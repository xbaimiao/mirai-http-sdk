package com.xbaimiao.mirai.packet.enums

enum class MessageType(internal val command: String) {
    GROUP("sendGroupMessage"),
    FRIEND("sendFriendMessage"),
    TEMP("sendTempMessage");

    companion object {
        fun fromCommand(command: String) = values().first { it.command == command }
    }

}
