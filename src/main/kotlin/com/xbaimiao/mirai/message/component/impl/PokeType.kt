package com.xbaimiao.mirai.message.component.impl

enum class PokeType(poke: String) {
    Poke("Poke"), ShowLove("ShowLove"), Like("Like"), Heartbroken("Heartbroken"), SixSixSix("SixSixSix"), FangDaZhao("FangDaZhao");

    companion object {
        fun typeToString(type: PokeType): String {
            return type.toString()
        }

        fun formString(type: String): PokeType {
            return valueOf(type)
        }
    }
}