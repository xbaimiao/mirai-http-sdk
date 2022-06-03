package com.xbaimiao.mirai.message.component.impl

import com.xbaimiao.mirai.message.component.impl.MusicType

enum class MusicType(music: String) {
    Netease("NeteaseCloudMusic"), QQ("QQMusic"), Kugou("KugouMusic");

    companion object {
        fun MusicTypeConverter(type: MusicType): String {
            if (type == Netease) {
                return "NeteaseCloudMusic"
            } else if (type == QQ) {
                return "QQMusic"
            } else if (type == Kugou) {
                return "KugouMusic"
            }
            return "NeteaseCloudMusic"
        }
    }
}