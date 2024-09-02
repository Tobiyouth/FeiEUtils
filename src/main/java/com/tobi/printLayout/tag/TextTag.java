package com.tobi.printLayout.tag;

import com.tobi.printLayout.Utils;
import lombok.Getter;
import lombok.Setter;

/**
 * 文本打印标签
 *
 * @Author ljtian
 * @Date 2024/9/2 21:54
 **/
@Setter
@Getter
public class TextTag extends TagBase {

    public TextTag(int x, int y,  int w, int h, String content) {
        super.setX(x);
        super.setY(y);
        this.w = w;
        this.h = h;
        this.content = content;
    }

    /**
     * 字体
     * 1、 8×12 dot 英数字体
     * 2、 12×20 dot 英数字体
     * 3、 16×24 dot 英数字体
     * 4、 24×32 dot 英数字体
     * 5、 32×48 dot 英数字体
     * 6、 14×19 dot 英数字体 OCR-B
     * 7、 21×27 dot 英数字体 OCR-B
     * 8、 14×25 dot 英数字体 OCR-A
     * 9、 9×17 dot 英数字体
     * 10、 12×24 dot 英数字体
     * 11、 繁体中文 24×24Font(大五码)
     * 12、 简体中文 24×24Font(GB 码)
     * 13、 韩文 24×24Font(KS 码)
     */
    private int font = 12;

    /**
     * 文字宽度放大倍率1-10
     */
    private int w;

    /**
     * 文字高度放大倍率1-10
     */
    private int h;

    /**
     * 为文字旋转角度(顺时针方向)：
     * 0 0度
     * 90 90度
     * 180 180度
     * 270 270度
     * （默认为0）
     */
    private int r;

    /**
     * 文本内容
     */
    private String content;

    @Override
    public String toString() {
        return Utils.getTextTag(getX(), getY(), w, h, content);
    }
}
