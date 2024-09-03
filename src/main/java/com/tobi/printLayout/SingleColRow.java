package com.tobi.printLayout;

import com.tobi.printLayout.constant.ALIGN;

/**
 * 只包含一列的行
 *
 * @Author ljtian
 * @Date 2024/9/3 20:34
 **/
public class SingleColRow {
    private final RowBuilder rowBuilder = RowBuilder.newBuilder();
    private final ColBuilder colBuilder = ColBuilder.newBuilder();

    public static SingleColRow newRow(String content){
        SingleColRow singleColRow = new SingleColRow();
        singleColRow.colBuilder.content(content);
        return singleColRow;
    }

    public Row build(){
        return rowBuilder.addCols(
                colBuilder.build()
        ).build();
    }

    /**
     * 文本左对齐
     *
     * @Author ljtian
     * @Date 2024/9/3 20:35
     **/
    public SingleColRow left(){
        colBuilder.align(ALIGN.LEFT);
        return this;
    }

    /**
     * 文本右对齐
     *
     * @Author ljtian
     * @Date 2024/9/3 20:35
     **/
    public SingleColRow right(){
        colBuilder.align(ALIGN.RIGHT);
        return this;
    }

    /**
     * 文本居中
     *
     * @Author ljtian
     * @Date 2024/9/3 20:35
     **/
    public SingleColRow center(){
        colBuilder.align(ALIGN.CENTER);
        return this;
    }

    /**
     * 文字宽度放大
     *
     * @Author ljtian
     * @Date 2024/9/3 20:36
     **/
    public SingleColRow fontWidthScale(int i){
        colBuilder.fontWidthScale(i);
        return this;
    }

    /**
     * 文字高度放大
     *
     * @Author ljtian
     * @Date 2024/9/3 20:36
     **/
    public SingleColRow fontHeightScale(int i){
        colBuilder.fontHeightScale(i);
        return this;
    }

    /**
     * 文字宽高一起放大
     *
     * @Author ljtian
     * @Date 2024/9/3 20:36
     **/
    public SingleColRow fontScale(int i){
        colBuilder.fontWidthScale(i);
        colBuilder.fontHeightScale(i);

        return this;
    }
}
