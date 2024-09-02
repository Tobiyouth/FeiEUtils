package com.tobi.printLayout;


import com.tobi.printLayout.constant.DIRECTION;

import java.util.List;
import java.util.Objects;

import static com.tobi.printLayout.constant.Constants.DIVIDE_DEFAULT;


/**
 * 打印画布 构造器
 *
 * @Author ljtian
 * @Date 2024/8/31 23:28
 **/
public class PrintCanvasBuilder {
    private PrintCanvas printContent = new PrintCanvas();

    public static PrintCanvasBuilder newBuilder() {
        return new PrintCanvasBuilder();
    }

    public PrintCanvasBuilder paddingRight(int paddingRight) {
        printContent.setPaddingRight(paddingRight);
        return this;
    }

    public PrintCanvasBuilder paddingTop(int paddingTop) {
        printContent.setPaddingTop(paddingTop);
        return this;
    }

    public PrintCanvasBuilder paddingBottom(int paddingBottom) {
        printContent.setPaddingBottom(paddingBottom);
        return this;
    }

    public PrintCanvasBuilder paddingLeft(int paddingLeft) {
        printContent.setPaddingLeft(paddingLeft);
        return this;
    }

    public PrintCanvasBuilder paddings(int[] paddings) {
        if (Objects.isNull(paddings) || paddings.length < 4) {
            return this;
        }

        printContent.setPaddings(paddings);
        return this;
    }


    public PrintCanvasBuilder width(int width) {
        printContent.setWidth(width);
        return this;
    }


    public PrintCanvasBuilder height(int i) {
        printContent.setHeight(i);
        return this;
    }

    public PrintCanvasBuilder direction(DIRECTION direction) {
        printContent.setDirection(direction);
        return this;
    }

    public PrintCanvasBuilder lineHeight(int lineHeight){
        printContent.setLineHeight(lineHeight);
        return this;
    }

    public PrintCanvasBuilder fontWidthScale(int fontWidthScale){
        printContent.setFontWidthScale(fontWidthScale);
        return this;
    }

    public PrintCanvasBuilder fontHeightScale(int fontHeightScale){
        printContent.setFontHeightScale(fontHeightScale);
        return this;
    }

    public PrintCanvasBuilder addRow(Row row){
        printContent.getRows().add(row);
        return this;
    }

    public PrintCanvasBuilder addRows(List<Row> rows){
        printContent.getRows().addAll(rows);
        return this;
    }

    /**
     * 添加自定义分隔线
     *
     * @param symbol 分隔线符号
     * @Author ljtian
     * @Date 2024/9/2 19:06
     **/
    public PrintCanvasBuilder divide(char symbol) {
        this.addRow(Row.newDivide(symbol));
        return this;
    }

    /**
     * 添加分隔线
     *
     * @Author ljtian
     * @Date 2024/9/2 19:06
     **/
    public PrintCanvasBuilder divide(){
        divide(DIVIDE_DEFAULT);
        return this;
    }

    public PrintCanvas build(){
        printContent.getRows().forEach(r-> r.setPrintCanvas(printContent));
        return printContent;
    }


}




