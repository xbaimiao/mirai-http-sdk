package com.xbaimiao.mirai.example

import com.xbaimiao.mirai.event.GroupMessageEvent
import com.xbaimiao.mirai.eventbus.SubscribeHandler
import com.xbaimiao.mirai.eventbus.SubscribeListener
import com.xbaimiao.mirai.eventbus.SubscribePriority
import com.xbaimiao.mirai.message.component.impl.Music
import com.xbaimiao.mirai.message.component.impl.MusicType
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot
import com.xbaimiao.mirai.packet.impl.websocket.WsInfo

private lateinit var bot: WebSocketBot

fun main() {
    val webSocketBotConfig = WsInfo("http://localhost:8080/", 1000000, "AUTHKEY")
    bot = WebSocketBot(webSocketBotConfig).connect()
    bot.join()
    bot.eventChancel.subscribe(object : SubscribeListener {
        @SubscribeHandler(priority = SubscribePriority.NORMAL)
        fun groupMessageEvent(event: GroupMessageEvent) {
            event.group.sendMessage(
                Music(
                    MusicType.QQ,
                    "Test",
                    "Powered by FlyProject",
                    "https://localhost",
                    "http://y.gtimg.cn/music/photo_new/T002R300x300M000001MyK3Y47zLur.jpg",
                    "https://loaclhost/test.mp3",
                    "[测试] 测试"
                )
            )
        }
    })
}


