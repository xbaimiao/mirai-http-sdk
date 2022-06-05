package com.xbaimiao.mirai.message.component.impl

enum class PokeType(poke: String) {
    Poke("Poke"), ShowLove("ShowLove"), Like("Like"), Heartbroken("Heartbroken"), SixSixSix("SixSixSix"), FangDaZhao("FangDaZhao");

    companion object {
        fun TypeToString(type: PokeType): String {
            return type.toString()
        }

        fun StringToType(type: String?): PokeType {
            return valueOf(type!!)
        }
    }
}