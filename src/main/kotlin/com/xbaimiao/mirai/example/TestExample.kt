package com.xbaimiao.mirai.example

import com.xbaimiao.mirai.event.BotJoinGroupEvent
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
    val webSocketBotConfig = WsInfo("http://127.0.0.1", 1000000000, "123456")
    bot = WebSocketBot(webSocketBotConfig).connect()
    bot.join()
    bot.eventChancel.subscribe(object : SubscribeListener {
        @SubscribeHandler(priority = SubscribePriority.NORMAL)
        fun test(event: GroupMessageEvent){

        }
    })
}


