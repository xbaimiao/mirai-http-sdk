package com.xbaimiao.mirai.example

import com.xbaimiao.mirai.event.GroupMessageEvent
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.message.component.impl.At
import com.xbaimiao.mirai.message.component.impl.AtAll
import com.xbaimiao.mirai.message.component.impl.PlainText
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot
import com.xbaimiao.mirai.packet.impl.websocket.WsInfo

private lateinit var bot: WebSocketBot

fun main() {
    val wsInfo = WsInfo("http://run.xbaimiao.com:8099/", 2157207381, "INITKEYCgTu5Bcx")
    bot = WebSocketBot(wsInfo).connect()
    bot.join()
    bot.eventChancel.subscribe<GroupMessageEvent> {
        var boolean1 = false
        for (baseComponent in this.component.toList()) {
            if (baseComponent is PlainText) {
                if (baseComponent.string == "禁言") {
                    boolean1 = true
                }
            }
        }
        for (baseComponent in this.component.toList()) {
            if (baseComponent is At) {
                if (boolean1){
                    group.getMember(baseComponent.target).thenAccept {
                        it!!.mute(600)
                    }
                }
            }
        }
        if (plainText == "回复我") {
            group.quoteMessage(Component.text("好"), "${this.messageSource.messageId}")
        }
    }
}


