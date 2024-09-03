# FeiEUtils
飞蛾标签打印机工具类, 提供布局功能。

### 容器

    一张标签纸包含一个画布(Canvas),
    
    一张画布包含多行(Row),
    
    一行包含多列(Col).

    画布/行/列统称为容器.

#### 容器属性
    
    padding: 内边距, 容器单独设置, 不继承
    lineHeight: 文本行高, 子容器不设置会继承父容器的值
    align: 文本对齐方式, 子容器不设置会继承父容器的值, 默认左对齐
    fontHeightScale: 字高度放大倍率, 子容器不设置会继承父容器的值, 默认一倍
    fontWidthScale: 文字宽度放大倍率, 子容器不设置会继承父容器的值, 默认一倍

### PrintCanvas： 画布
#### 画布专属属性: 
    width: 标签宽
    height: 标签高度
    direction: 出纸方向

### Row: 行
    

### Col: 列
#### 列专属属性:
    content: 打印的文本内容
    percent: 当前列占整行百分比, 0 ~ 1

## 使用例子
