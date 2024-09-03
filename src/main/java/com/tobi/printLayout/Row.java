package com.tobi.printLayout;

import com.tobi.printLayout.tag.TextTag;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 打印每行的内容容器
 *
 * @Author ljtian
 * @Date 2024/8/31 23:24
 **/
@Getter
@Setter
public class Row extends ContainerBase {
    public static RowBuilder builder(){
        return new RowBuilder();
    }

    private PrintCanvas printCanvas;

    private List<Col> cols = new ArrayList<>();

    /**
     * 当前行打印的 x 轴坐标, 相对于画布, 不包含画布的paddingLeft
     */
    private int x = 0;

    /**
     * 获取行的宽度
     *
     * @Author ljtian
     * @Date 2024/9/1 20:41
     **/
    public int getRowWidth() {
        int paddingX = getPaddingLeft() + getPaddingRight();

        if (cols.isEmpty()) {
            return paddingX;
        }

        return cols.stream().mapToInt(Col::getColWidth).sum() + paddingX;
    }

    /**
     * 获取可绘制内容的宽度
     *
     * @Author ljtian
     * @Date 2024/9/1 22:08
     **/
    public int getContentWidth(){
        return getPrintCanvas().getContentWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 获取行高度：
     * row 上下内边距加上 最高的 col
     *
     * @Author ljtian
     * @Date 2024/9/1 21:20
     **/
    public int getRowHeight() {
        int paddingY = getPaddingTop() + getPaddingBottom();

        if (cols.isEmpty()) {
            return paddingY;
        }

        Optional<Col> max = cols.stream().max(Comparator.comparingInt(Col::getColHeight));
        return max.map(col -> col.getColHeight() + paddingY).orElse(paddingY);
    }

    /**
     * 获取当前行的打印内容
     *
     * @Author ljtian
     * @Date 2024/9/1 21:29
     **/
    public List<TextTag> getRowPrintContent() {
        if (cols.isEmpty()) {
            return Collections.emptyList();
        }

        List<TextTag> textTagList = new ArrayList<>();
        for (Col col : cols) {
            int colWidth = (int) (getContentWidth() * col.getPercent());
            textTagList.addAll(col.getColPrintContent(colWidth));
            setX(getX() + colWidth);
        }

        return textTagList;
    }


    /**
     * 创建分隔线
     *
     * @Author ljtian
     * @Date 2024/9/2 19:13
     **/
    public static Row newDivide(Character symbol){
        Row row = new Row();
        Col col = new Col();
        col.setRow(row);
        col.setDivide(true);

        if (Objects.nonNull(symbol)){
            col.setDivideSymbol(symbol);
        }

        row.cols.add(col);

        return row;
    }
}
