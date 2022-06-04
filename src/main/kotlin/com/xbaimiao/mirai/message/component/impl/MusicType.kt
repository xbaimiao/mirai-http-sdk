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

        fun StringToMusicType(type: String): MusicType {
            if (type.equals("NeteaseCloudMusic")) {
                return Netease;
            } else if (type.equals("QQMusic")) {
                return QQ;
            } else if (type.equals("KugouMusic")) {
                return Kugou;
            }
            return Netease;
        }
    }
}