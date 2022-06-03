package com.xbaimiao.mirai.example

import com.google.gson.JsonParser
import com.xbaimiao.mirai.config.WebSocketBotConfig
import com.xbaimiao.mirai.event.GroupMessageEvent
import com.xbaimiao.mirai.eventbus.SubscribeHandler
import com.xbaimiao.mirai.eventbus.SubscribeListener
import com.xbaimiao.mirai.eventbus.SubscribePriority
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.impl.Music
import com.xbaimiao.mirai.message.component.impl.MusicType
import com.xbaimiao.mirai.message.serialize.MiraiSerializer
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot

private lateinit var bot: WebSocketBot

fun main() {
    val webSocketBotConfig = WebSocketBotConfig("http://localhost:8080/", 1000000, "AUTHKEY")
    bot = WebSocketBot(webSocketBotConfig).connect()
    bot.join()
    bot.eventChancel.registerSubscribeListener(object : SubscribeListener {
        @SubscribeHandler(priority = SubscribePriority.NORMAL)
        fun groupMessageEvent(event: GroupMessageEvent) {
            event.group.sendMessage(Music(MusicType.QQ,"Test","Powered by FlyProject","https://localhost","http://y.gtimg.cn/music/photo_new/T002R300x300M000001MyK3Y47zLur.jpg","https://loaclhost/test.mp3","[测试] 测试"))
        }
    })
}


