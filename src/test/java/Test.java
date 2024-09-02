import com.tobi.printLayout.ColBuilder;
import com.tobi.printLayout.PrintCanvas;
import com.tobi.printLayout.PrintCanvasBuilder;
import com.tobi.printLayout.Row;
import com.tobi.printLayout.RowBuilder;
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
                .addRow(
                        RowBuilder.newBuilder()
                                .addCol(
                                        ColBuilder.newBuilder()
                                                .percent(0.35f)
                                                .content("hello world!")
                                                .build()
                                )
                                .addCol(
                                        ColBuilder.newBuilder()
                                                .percent(0.65f)
                                                .fontWidthScale(2)
                                                .paddingTop(50)
                                                .align(ALIGN.RIGHT)
                                                .content("Tobiywewuth")
                                                .build()
                                )
                                .build()
                )
                .divide()
                .addRow(getRow(ALIGN.LEFT))
                .addRow(getRow(ALIGN.RIGHT))
                .addRow(getRow(ALIGN.LEFT))
                .addRow(getRow(ALIGN.LEFT))


                .divide('┅')
                .addRow(
                        RowBuilder.newBuilder()
                                .paddingRight(0)
                                .addCol(
                                        ColBuilder.newBuilder()
                                                .paddingRight(0)
                                                .align(ALIGN.RIGHT)
                                                .content(new Date().toString())
                                                .build()
                                ).build()
                )
                .build();

        content.setY(340);
        List<String> printContent = content.generalPrintContent();
        for (String s : printContent) {
            System.out.println(s);
        }
    }

    private static Row getRow(ALIGN align) {
        Row row = RowBuilder.newBuilder()
                .lineHeight(26)
                .addCol(
                        ColBuilder.newBuilder()
                                .align(align)
                                .content("11111111113333333333")
                                .build()
                ).build();
        return row;
    }
}
