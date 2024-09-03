import com.tobi.printLayout.Col;
import com.tobi.printLayout.PrintCanvas;
import com.tobi.printLayout.PrintCanvasBuilder;
import com.tobi.printLayout.Row;
import com.tobi.printLayout.SingleColRow;
import com.tobi.printLayout.constant.ALIGN;
import com.tobi.printLayout.constant.DIRECTION;

import java.util.Date;
import java.util.List;

public class Test {
    @org.junit.Test
    public void test() {
        PrintCanvas content = PrintCanvasBuilder
                .newBuilder()
                .width(55)
                .height(80)
                .paddingTop(10)
                .paddingLeft(10)
                .paddingRight(0)
                .direction(DIRECTION.REVERSE)
                .addRows(
                        Row.builder()
                                .addCols(
                                        Col.builder()
                                                .percent(0.35f)
                                                .content("hello world!")
                                                .build()
                                ).build()
                )
                .addRows(
                        SingleColRow.newRow("hhh").fontScale(2).right().build()
                )
                .divide()
                .addRows(getRow(ALIGN.LEFT))
                .addRows(getRow(ALIGN.RIGHT))
                .addRows(getRow(ALIGN.LEFT))
                .addRows(getRow(ALIGN.LEFT))


                .divide('┅')
                .addRows(
                        Row.builder()
                                .paddingRight(0)
                                .addCols(
                                        Col.builder()
                                                .paddingRight(0)
                                                .align(ALIGN.RIGHT)
                                                .content(new Date().toString())
                                                .build()
                                ).build()
                )
                .build();

        List<String> printContent = content.generalPrintContent();
        for (String s : printContent) {
            System.out.println(s);
        }
    }

    private static Row getRow(ALIGN align) {
        Row row = Row.builder()
                .lineHeight(26)
                .addCols(
                        Col.builder()
                                .align(align)
                                .content("11111111113333333333")
                                .build()
                ).build();
        return row;
    }
}
