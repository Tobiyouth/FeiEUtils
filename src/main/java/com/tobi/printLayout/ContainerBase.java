package com.tobi.printLayout;

import com.tobi.printLayout.constant.ALIGN;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 内容容器基类
 *
 * @Author ljtian
 * @Date 2024/9/1 23:09
 **/
@Getter
@Setter
public abstract class ContainerBase {
    /*
     * 内边距点数, 上, 右, 下, 左
     */
    protected int[] paddings = {0, 0, 0, 0};

    /**
     * 文字宽度放大倍率1-10（默认为1）, 设置为 0 时继承父容器的值
     * 优先级: col > row > content
     * 默认值 {@link printLayout.constant.Constants#LINE_HEIGHT_SCALE_DEFAULT }
     */
    protected int fontWidthScale = 0;

    /*
     * 文字高度放大倍率1-10, 设置为 0 时继承父容器的值
     * 优先级: col > row > content
     */
    protected int fontHeightScale = 0;

    /*
     * 文本对齐方式, 默认左对齐，, 设置为 NONE 时继承父容器的值
     *  优先级: col > row > content
     */
    protected ALIGN align = ALIGN.NONE;

    /**
     * 文本行高， 默认等于一倍文字高度
     */
    protected int lineHeight = 0;

    public void setPaddings(int[] paddings) {
        if (Objects.isNull(paddings) || paddings.length < 4) {
            return;
        }

        this.paddings = paddings;
    }

    public void setPaddingTop(int paddingTop) {
        paddings[0] = paddingTop;
    }

    public int getPaddingTop() {
        return paddings[0];
    }

    public void setPaddingRight(int paddingRight) {
        paddings[1] = paddingRight;
    }

    public int getPaddingRight() {
        return paddings[1];
    }

    public void setPaddingBottom(int paddingBottom) {
        paddings[2] = paddingBottom;
    }

    public int getPaddingBottom() {
        return paddings[2];
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddings[3] = paddingLeft;
    }

    public int getPaddingLeft() {
        return paddings[3];
    }
}
