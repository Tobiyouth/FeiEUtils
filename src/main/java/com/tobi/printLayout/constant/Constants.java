package com.tobi.printLayout.constant;

public final class Constants {
    /**
     * 字体默认宽度和高度所占点数
     */
    public  final static int FONT_W_DOTS = 24;
    public  final static int FONT_H_DOTS = 24;

    /**
     * 字体默认缩放, 1倍
     */
    public final static int FONT_DEFAULT_SCALE = 1;

    /**
     * 文本行高比例
     */
    public final static int LINE_HEIGHT_SCALE_DEFAULT = FONT_H_DOTS + 0;

    /**
     * 文本标签
     */
    public final static String TEXT_TAG_TEMPLATE = "<TEXT x=\"%d\" y=\"%d\" font=\"12\" w=\"%d\" h=\"%d\" r=\"0\">%s</TEXT>";

    /**
     * 打印方向标签
     */
    public final static String DIRECTION_TAG_TEMPLATE = "<DIRECTION>%d</DIRECTION>";

    /**
     * 默认分隔符
     */
    public final static char DIVIDE_DEFAULT= '━';
}
