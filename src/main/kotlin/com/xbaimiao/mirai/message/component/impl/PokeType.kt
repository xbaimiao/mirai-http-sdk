package com.xbaimiao.mirai.message.component.impl

enum class PokeType(poke: String) {
    Poke("戳一戳"),
    ShowLove("比心"),
    Like("点赞"),
    Heartbroken("心碎"),
    SixSixSix("666"),
    FangDaZhao("放大招"),

    // Update from mirai-core-api
    ChuoYiChuo("戳一戳"),
    BiXin("比心"),
    DianZan("点赞"),
    XinSui("心碎"),
    LiuLiuLiu("666"),
    BaoBeiQiu("宝贝球"),
    Rose("玫瑰花"),
    ZhaoHuanShu("召唤术"),
    RangNiPi("让你皮"),
    JieYin("结印"),
    ShouLei("手雷"),
    GouYin("勾引"),
    ZhuaYiXia("抓一下"),
    SuiPing("碎屏"),
    QiaoMen("敲门");

    companion object {
        fun typeToString(type: PokeType): String {
            return type.toString()
        }

        fun formString(type: String): PokeType {
            return valueOf(type)
        }
    }
}