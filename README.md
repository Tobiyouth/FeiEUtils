# FeiEUtils
飞蛾标签打印机工具类, 提供布局功能。

### 容器

    有画布/行/列三种容器, 一张标签纸包含一个画布(Canvas), 一张画布包含多行(Row),
    一行包含多列(Col), 有且只有列用于放文本内容.

    

#### 容器属性
    
    padding: 内边距, 容器单独设置, 子容器不继承父容器的值.
    lineHeight: 文本行高, 子容器不设置会继承父容器的值, 默认等于为文字的高度 +4.
    align: 文本对齐方式, 子容器不设置会继承父容器的值, 默认左对齐.
    fontHeightScale: 字高度放大倍率, 子容器不设置会继承父容器的值, 默认一倍.
    fontWidthScale: 文字宽度放大倍率, 子容器不设置会继承父容器的值, 默认一倍.

### PrintCanvas： 画布
#### 画布专属属性: 
    width: 标签宽
    height: 标签高度
    direction: 打印方向

### Row: 行
    没有专有属性

### Col: 列
#### 列专属属性:
    content: 打印的文本内容
    percent: 当前列占整行百分比, 0 ~ 1, 默认为1, 既占一行.

## 使用例子

```java

 PrintCanvas content = PrintCanvasBuilder
            .newBuilder()
            //设置画布宽高, 单位mm
            .width(55)
            .height(80)
            //设置画布内边距
            .paddingTop(10)
            .paddingLeft(10)
            .paddingRight(0)
            //设置画布打印方向, 反方向
            .direction(DIRECTION.REVERSE)
            .addRows(
                    //添加只有一列的行, 字体放大两倍, 居中对齐
                    SingleColRow.newRow("华谊钦州食堂").fontScale(2).center().build()
            )
            .addRows(
                    //添加包含两列的行
                    Row.builder()
                            .addCols(
                                    //打印当前日期, 文字高度放大两倍, 该列占本行一半宽度
                                    Col.builder().content(SimpleDateFormat.getDateInstance().format(new Date()))
                                            .fontHeightScale(2).percent(0.5f)
                                            .build(),
                                    //打印随机数, 该列占本行一半宽度
                                    Col.builder().content(String.valueOf(Math.ceil(Math.random() * 1000)))
                                            .percent(0.5f).build()
                            ).build()
            )
            //添加一条分割线
            .divide()
            //添加菜品标题
            .addRows(
                    Row.builder()
                            .addCols(
                                    Col.builder().content("菜品").percent(0.5f).build(),
                                    Col.builder().content("单价").percent(0.15f).build(),
                                    Col.builder().content("数量").percent(0.15f).build(),
                                    Col.builder().content("金额").percent(0.15f).build()
                            )
                            .build()
            )
            //添加一条有*号组成的分割线
            .divide('*')
            //添加两行菜品和金额, 菜品文字放大两倍
            .addRows(
                    Row.builder()
                            .addCols(
                                    Col.builder().content("红烧排骨").fontScale(2).percent(0.5f).build(),
                                    Col.builder().content("1.00").percent(0.15f).build(),
                                    Col.builder().content("1").percent(0.15f).build(),
                                    Col.builder().content("1.00").percent(0.15f).build()
                            )
                            .build(),
                    Row.builder()
                            .addCols(
                                    Col.builder().content("黄焖鸡米饭").fontScale(2).percent(0.5f).build(),
                                    Col.builder().content("3.00").percent(0.15f).build(),
                                    Col.builder().content("2").percent(0.15f).build(),
                                    Col.builder().content("6.00").percent(0.15f).build()
                            )
                            .build()
            )
            .divide()
            .addRows(
                    SingleColRow.newRow("地址:华南理工大学b8实验室102").fontScale(2).build(),
                    SingleColRow.newRow("手机:13048074047").fontScale(2).build()
            )
            .build();

    //获取最终的打印内容,
    //当打印内容超过一个标签是会自动分页, 所以这里返回List, 一条记录表示一个标签.      
    List<String> printContent = content.generalPrintContent();
    for (String s : printContent) {
        System.out.println(s);
    }

```
