package com.tobi.printLayout;


import com.tobi.printLayout.constant.Constants;
import com.tobi.printLayout.constant.DIRECTION;

import java.util.Objects;

public final class Utils {
    /**
     * 是否是全角字符
     *
     * @Author ljtian
     * @Date 2024/9/1 11:58
     **/
    public static boolean isFullWidth(char ch) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);
        return (
                block == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
                        block == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ||
                        ch >= '\u3400' && ch <= '\u4DBF' || // CJK Extensions A
                        ch >= '\u4E00' && ch <= '\u9FFF' || // CJK Unified Ideographs
                        ch >= '\uF900' && ch <= '\uFAFF' || // CJK Compatibility Ideographs
                        ch >= '\uFE30' && ch <= '\uFE4F' || // CJK Compatibility Forms
                        ch >= '\uFF00' && ch <= '\uFFEF'    // Fullwidth Forms
        );
    }

    /**
     * 获取文本标签
     *
     * @Author ljtian
     * @Date 2024/9/1 21:47
     **/
    public static String getTextTag(int x, int y, int wScale, int hScale, String txt){
        return String.format(Constants.TEXT_TAG_TEMPLATE, x, y, wScale, hScale, txt);
    }

    /**
     * 计算文本宽度
     *
     * @Author ljtian
     * @Date 2024/9/2 20:49
     **/
    public static int calcTextWidth(String content, float scale) {
        int width = 0;

        if (Objects.isNull(content)) {
            return width;
        }

        for (char c : content.toCharArray()) {
            if (Utils.isFullWidth(c)) {
                width += (Constants.FONT_W_DOTS * scale);
            } else {
                width += (Constants.FONT_W_DOTS / 2 * scale);
            }
        }

        return width;
    }

    /**
     * 获取打印方向标签内容
     *
     * @Author ljtian
     * @Date 2024/9/1 21:47
     **/
    public static String getDirectionTag(DIRECTION direction){
        return String.format(Constants.DIRECTION_TAG_TEMPLATE, direction.getValue());
    }
}
