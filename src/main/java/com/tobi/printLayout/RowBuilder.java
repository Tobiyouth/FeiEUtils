package com.tobi.printLayout;

import java.util.List;

/**
 * Row 构造器

 *
 * @Author ljtian
 * @Date 2024/8/31 23:24
 **/
public class RowBuilder {
    private Row row = new Row();

    public static RowBuilder newBuilder(){
        return new RowBuilder();
    }


    public RowBuilder lineHeight(int lineHeight){
        this.row.setLineHeight(lineHeight);
        return this;
    }

    public RowBuilder paddingRight(int paddingRight) {
        this.row.setPaddingRight(paddingRight);
        return this;
    }

    public RowBuilder paddingTop(int paddingTop) {
        this.row.setPaddingTop(paddingTop);
        return this;
    }

    public RowBuilder paddingBottom(int paddingBottom) {
        this.row.setPaddingBottom(paddingBottom);
        return this;
    }

    public RowBuilder paddingLeft(int paddingLeft) {
        this.row.setPaddingLeft(paddingLeft);
        return this;
    }

    public RowBuilder paddings(int[] paddings) {
        this.row.setPaddings(paddings);
        return this;
    }

    public RowBuilder fontWidthScale(int fontWidthScale){
        if (fontWidthScale > 10){
            fontWidthScale = 10;
        }

        this.row.setFontWidthScale(fontWidthScale);
        return this;
    }

    public RowBuilder fontHeightScale(int fontHeightScale){
        if (fontHeightScale > 10){
            fontHeightScale = 10;
        }

        this.row.setFontHeightScale(fontHeightScale);
        return this;
    }

    public RowBuilder addCol(Col col){
        this.row.getCols().add(col);
        return this;
    }

    public RowBuilder addCols(List<Col> col){
        row.getCols().addAll(col);
        return this;
    }

    public Row build(){
        row.getCols().forEach(r-> r.setRow(row));
        return row;
    }
}
