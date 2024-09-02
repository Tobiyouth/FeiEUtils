package com.tobi.printLayout.constant;

import lombok.Getter;

/**
 * 设定打印时出纸和打印字体的方向
 *
 * @Author ljtian
 * @Date 2024/8/31 23:15
 **/
@Getter
public enum DIRECTION {
    /*
     *正向
     */
    FORWARD(1),

    /*
     * 反向
     */
    REVERSE(0);

    private int value;
    DIRECTION(int i) {
        value = i;
    }
}
