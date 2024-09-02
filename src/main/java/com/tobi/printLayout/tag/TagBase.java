package com.tobi.printLayout.tag;

import lombok.Getter;
import lombok.Setter;

/**
 * 打印标签基类
 *
 * @Author ljtian
 * @Date 2024/9/2 21:49
 **/
@Getter
@Setter
public abstract class TagBase {
    /**
     * 水平方向起始点坐标
     */
    private int x;

    /**
     * 垂直方向起始点坐标
     */
    private int y;

    public abstract String toString();
}
