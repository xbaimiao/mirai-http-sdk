package com.xbaimiao.mirai.example

import com.xbaimiao.mirai.packet.impl.websocket.WsInfo
import com.xbaimiao.mirai.event.GroupMessageEvent
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot

private lateinit var bot: WebSocketBot

fun main() {
    val wsInfo = WsInfo("http://run.xbaimiao.com:8099/", 2157207381, "INITKEYCgTu5Bcx")
    bot = WebSocketBot(wsInfo).connect()
    bot.join()
    bot.getGroup(796716964)!!.getMembers().thenAcceptAsync {
        it.random().also {
            it.mute(10).thenAcceptAsync {
                println(it)
            }
            Thread.sleep(1000)
            it.unmute().thenAcceptAsync {
                println(it)
            }
        }
    }
    bot.eventChancel.subscribe<GroupMessageEvent> {
        // 如果消息等于 “你马” 禁言他 10分钟
        if (this.plainText == "你马") {
            this.group.getMembers().thenAcceptAsync {

            }
        }
        if (plainText == "回复我") {
            group.quoteMessage(Component.text("好"), "${this.messageSource.messageId}")
        }
    }
}


