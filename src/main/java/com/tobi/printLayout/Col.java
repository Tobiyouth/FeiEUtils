package com.tobi.printLayout;

import com.tobi.printLayout.constant.ALIGN;
import com.tobi.printLayout.constant.Constants;
import com.tobi.printLayout.tag.TextTag;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.tobi.printLayout.constant.Constants.DIVIDE_DEFAULT;


/**
 * 行里面的列容器
 *
 * @Author ljtian
 * @Date 2024/8/31 23:33
 **/
@Getter
@Setter
public class Col extends ContainerBase {
    /**
     * 父容器
     */
    private Row row;

    /**
     * 是否是分隔线， 默认不是
     */
    private boolean isDivide = false;

    /**
     * 分隔线符号
     */
    private char divideSymbol = DIVIDE_DEFAULT;

    /*
     * 打印的文本内容
     */
    private String content = "";

    /**
     * 当 col 宽度装不下content时拆分拆分content
     */
    private List<String> splitContentList = new ArrayList<>();
    private int splitContentIndex = 0;

    /*
     * 当前列占整行百分比, 0 ~ 1
     */
    private float percent = 1f;

    /**
     * 当前列宽度, 文本宽度加上 padding
     */
    private int width = 0;


    /**
     * 获取列的宽度, 内容 + padding
     *
     * @Author ljtian
     * @Date 2024/9/1 21:16
     **/
    public int getColWidth() {
        int paddingX = getPaddingLeft() + getPaddingRight();
        return getTextWidth() + paddingX;
    }

    /**
     * 获取文本内容的宽度
     *
     * @Author ljtian
     * @Date 2024/9/1 22:08
     **/
    public int getTextWidth() {
        return Utils.calcTextWidth(getContent(), getFontWidthScale());
    }

    /**
     * 获取列的高度, 内容加padding
     *
     * @Author ljtian
     * @Date 2024/9/1 21:17
     **/
    public int getColHeight() {
        int paddingY = getPaddingTop() + getPaddingBottom();
        if (Objects.isNull(content)) {
            return paddingY;
        }

        return getLineHeight() *
                getFontHeightScale() *
                (splitContentList.isEmpty() ? 1 : splitContentList.size()) +
                paddingY;
    }


    /**
     * 字体缩放比例,
     * 优先级: col > row > content
     *
     * @Author ljtian
     * @Date 2024/9/1 19:49
     **/
    public int getFontWidthScale() {
        if (this.fontWidthScale > 0) {
            return this.fontWidthScale;
        }

        if (row.getFontWidthScale() > 0) {
            return row.getFontWidthScale();
        }

        if (row.getPrintCanvas().getFontWidthScale() > 0) {
            return row.getPrintCanvas().getFontWidthScale();
        }

        return Constants.FONT_DEFAULT_SCALE;
    }


    /**
     * 字体缩放比例,
     * 优先级: col > row > content
     *
     * @Author ljtian
     * @Date 2024/9/1 19:49
     **/
    public int getFontHeightScale() {
        if (this.fontHeightScale > 0) {
            return this.fontHeightScale;
        }

        if (row.getFontHeightScale() > 0) {
            return row.getFontHeightScale();
        }

        if (row.getPrintCanvas().getFontHeightScale() > 0) {
            return row.getPrintCanvas().getFontHeightScale();
        }

        return Constants.FONT_DEFAULT_SCALE;
    }

    /**
     * 对齐方式
     *
     * @Author ljtian
     * @Date 2024/9/1 19:49
     **/
    public ALIGN getAlign() {
        if (this.align != ALIGN.NONE) {
            return this.align;
        }

        if (row.getAlign() != ALIGN.NONE) {
            return row.getAlign();
        }

        if (row.getPrintCanvas().getAlign() != ALIGN.NONE) {
            return row.getPrintCanvas().getAlign();
        }

        return ALIGN.LEFT;
    }

    /**
     * 文本行高比例， 默认等于一倍文字高度
     *
     * @Author ljtian
     * @Date 2024/9/1 19:49
     **/
    public int getLineHeight() {
        if (this.lineHeight > 0) {
            return this.lineHeight;
        }

        if (row.getLineHeight() > 0) {
            return row.getLineHeight();
        }

        if (row.getPrintCanvas().getLineHeight() > 0) {
            return row.getPrintCanvas().getLineHeight();
        }

        return Constants.LINE_HEIGHT_SCALE_DEFAULT;
    }

    /**
     * 获取打印内容
     *
     * @param colWidth 当前列占行的宽度
     * @Author ljtian
     * @Date 2024/9/1 21:15
     **/
    public List<TextTag> getColPrintContent(int colWidth) {

        int x = row.getPrintCanvas().getPaddingLeft() +
                row.getPaddingLeft() +
                row.getX();


        int y = row.getPrintCanvas().getPaddingTop() +
                row.getPrintCanvas().getY() +
                row.getPaddingTop() +
                getPaddingTop();

        if (isDivide()) {
            return Collections.singletonList(generateDivide(x, y, colWidth));
        }

        splitContent(colWidth);

        List<TextTag> textTags = new ArrayList<>();
        for (String slitContent : splitContentList) {
            int offset = getOffset(colWidth, slitContent);
            textTags.add(
                    new TextTag(x + offset, y, getFontWidthScale(), getFontHeightScale(), slitContent)
            );
            y += getLineHeight() * getFontHeightScale();
        }


        return textTags;
    }

    /**
     * 拆分内容为多行
     *
     * @param colWidth
     */
    private void splitContent(int colWidth) {
        int colCanvasWidth = colWidth - getPaddingLeft() - getPaddingRight();
        if (getTextWidth() > colCanvasWidth) {
            int width = 0;

            StringBuilder contentItem = new StringBuilder();
            for (char c : content.toCharArray()) {
                int cWidth = 0;
                if (Utils.isFullWidth(c)) {
                    cWidth = (Constants.FONT_W_DOTS * getFontWidthScale());
                } else {
                    cWidth = (Constants.FONT_W_DOTS / 2 * getFontWidthScale());
                }

                width += cWidth;

                if (width > colWidth) {
                    splitContentList.add(contentItem.toString());
                    contentItem = new StringBuilder();
                    width = cWidth;
                }

                contentItem.append(c);
            }
            splitContentList.add(contentItem.toString());
        } else {
            splitContentList.add(getContent());
        }
    }

    /**
     * 生成分隔线
     *
     * @Author ljtian
     * @Date 2024/9/2 19:21
     **/
    private TextTag generateDivide(int x, int y, int colWidth) {
        int symbolCount = 0;
        if (Utils.isFullWidth(getDivideSymbol())) {
            symbolCount = colWidth / Constants.FONT_W_DOTS;
        } else {
            symbolCount = colWidth / Constants.FONT_W_DOTS * 2;
        }

        String content = StringUtils.repeat(String.valueOf(getDivideSymbol()), symbolCount);

        return new TextTag(x, y, getFontWidthScale(), getFontHeightScale(), content);
    }

    /**
     * 获取打印x轴在col容器的偏移量
     *
     * @Author ljtian
     * @Date 2024/9/1 23:08
     **/
    private int getOffset(int colWidth, String content) {
        if (getAlign() == ALIGN.LEFT) {
            return getPaddingLeft();
        }

        int contentWidth = Utils.calcTextWidth(content, getFontWidthScale());
        int colCanvasWidth = colWidth - getPaddingLeft() - getPaddingRight();

        if (getAlign() == ALIGN.CENTER) {
            if (colCanvasWidth > contentWidth) {
                return (colCanvasWidth - contentWidth) / 2;
            } else {
                return getPaddingLeft();
            }
        } else {
            if (colCanvasWidth > contentWidth) {
                return colCanvasWidth - contentWidth;
            } else {
                return getPaddingLeft();
            }
        }
    }
}
