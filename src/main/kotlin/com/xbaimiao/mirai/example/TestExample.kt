package com.xbaimiao.mirai.example

import com.xbaimiao.mirai.config.WebSocketBotConfig
import com.xbaimiao.mirai.event.GroupMessageEvent
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot

private lateinit var bot: WebSocketBot

fun main() {
    val webSocketBotConfig = WebSocketBotConfig("http://run.xbaimiao.com:8099/", 2157207381, "INITKEYCgTu5Bcx")
    bot = WebSocketBot(webSocketBotConfig).connect()
    bot.join()
//    bot.getGroups().thenAcceptAsync { groups ->
//        for (group in groups) {
//            if (group.id == 777905589L) {
//                val message = group.sendMessage("你好")
//                message.thenAcceptAsync {
//                    group.sendMessage(Component.text("回复自己"), "${it.messageId}")
//                }
//            }
//        }
//    }
    bot.eventChancel.subscribe<GroupMessageEvent> {
        if (plainText == "回复我") {
            group.quoteMessage(Component.text("好"), "${this.messageSource.messageId}")
        }
    }
}


