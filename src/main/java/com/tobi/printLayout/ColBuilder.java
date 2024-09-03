package com.tobi.printLayout;


import com.tobi.printLayout.constant.ALIGN;

import java.util.Objects;

/**
 * Row 构造器

 *
 * @Author ljtian
 * @Date 2024/8/31 23:24
 **/
public class ColBuilder {
    private Col col = new Col();

    static ColBuilder newBuilder(){
        return new ColBuilder();
    }

    public ColBuilder paddingRight(int paddingRight) {
        this.col.setPaddingRight(paddingRight);
        return this;
    }

    public ColBuilder paddingTop(int paddingTop) {
        this.col.setPaddingTop(paddingTop);
        return this;
    }

    public ColBuilder paddingBottom(int paddingBottom) {
        this.col.setPaddingBottom(paddingBottom);
        return this;
    }

    public ColBuilder paddingLeft(int paddingLeft) {
        this.col.setPaddingLeft(paddingLeft);
        return this;
    }

    public ColBuilder paddings(int[] paddings) {
        this.col.setPaddings(paddings);
        return this;
    }

    public ColBuilder fontWidthScale(int fontWidthScale){
        if (fontWidthScale > 10){
            fontWidthScale = 10;
        }

        this.col.setFontWidthScale(fontWidthScale);
        return this;
    }

    public ColBuilder fontHeightScale(int fontHeightScale){
        if (fontHeightScale > 10){
            fontHeightScale = 10;
        }

        this.col.setFontHeightScale(fontHeightScale);
        return this;
    }

    public ColBuilder fontScale(int scale){
        if (scale > 10){
            scale = 10;
        }

        this.col.setFontWidthScale(scale);
        this.col.setFontHeightScale(scale);
        return this;
    }

    public ColBuilder content(String content){
        if (Objects.isNull(content)){
            return this;
        }

        this.col.setContent(content);
        return this;
    }

    public ColBuilder align(ALIGN align){
        this.col.setAlign(align);
        return this;
    }

    public ColBuilder width(int width){
        this.col.setWidth(width);
        return this;
    }

    public ColBuilder percent(float percent){
        this.col.setPercent(percent);
        return this;
    }

    public Col build(){
        return col;
    }
}
