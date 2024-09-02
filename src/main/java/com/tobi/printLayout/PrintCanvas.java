package com.tobi.printLayout;

import com.tobi.printLayout.constant.DIRECTION;
import com.tobi.printLayout.tag.TextTag;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印布画容器
 * <p>
 * 简体中文 24×24Font(GB 码)
 *
 * @Author ljtian
 * @Date 2024/8/31 23:28
 **/
@Getter
@Setter
public class PrintCanvas extends ContainerBase {

    /*
     * 标签宽(dot)
     */
    private int width = 60 * 8;

    /*
     * 标签高度(dot)
     */
    private int height = 80 * 8;


    /*
     * 出纸方向
     */
    private DIRECTION direction = DIRECTION.FORWARD;


    /*
     * 每行内容
     */
    private List<Row> rows = new ArrayList<>();


    /**
     * 当前能继续打印的Y轴坐标, 不包含 paddingTop
     */
    private int y = 0;

    /**
     * 设置当前打印的 y 轴坐标
     *
     * @Author ljtian
     * @Date 2024/9/1 20:38
     **/
    public boolean setY(int y) {
        if (y > getHeight() - getPaddingBottom() - getPaddingTop()) {
            return false;
        }

        this.y = y;
        return true;
    }

    /**
     * 获取剩余可打印的高度
     *
     * @Author ljtian
     * @Date 2024/9/1 20:40
     **/
    public int getSurplusHeight() {
        return getHeight() - getPaddingBottom() - getY();
    }

    /**
     * 标签宽, 单位毫米
     *
     * @Author ljtian
     * @Date 2024/9/1 20:34
     **/
    public void setWidth(int mm) {
        this.width = mm * 8;
    }

    /**
     * 标签高,单位毫米
     *
     * @Author ljtian
     * @Date 2024/9/1 20:34
     **/
    public void setHeight(int mm) {
        this.height = mm * 8;
    }

    /**
     * 获取可绘制内容的宽度
     *
     * @Author ljtian
     * @Date 2024/9/1 22:06
     **/
    public int getContentWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 生成打印内容
     *
     * @Author ljtian
     * @Date 2024/9/1 21:36
     **/
    public List<String> generalPrintContent() {
        List<String> canvasStrings = new ArrayList<>();


        List<TextTag> canvasTags = new ArrayList<>();
        for (Row row : rows) {
            List<TextTag> rowTags = row.getRowPrintContent();
            int height = row.getRowHeight();
            if (getSurplusHeight() < height){
                canvasStrings.add(tagsToString(canvasTags));
                canvasTags.clear();
                rowTags.forEach(t -> t.setY(t.getY() - getY()));
                setY(0);
            }

            canvasTags.addAll(rowTags);
            setY(getY() + height);
        }
        canvasStrings.add(tagsToString(canvasTags));


        return canvasStrings;
    }

    private String tagsToString(List<TextTag> tags){
        StringBuilder content = new StringBuilder();
        content.append(Utils.getDirectionTag(this.direction)).append("\n");

        for (TextTag tag : tags) {
            content.append(tag.toString()).append("\n");
        }

        return content.toString();
    }

}




