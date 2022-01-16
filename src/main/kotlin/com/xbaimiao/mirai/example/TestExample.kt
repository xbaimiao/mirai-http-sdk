package com.xbaimiao.mirai.example

import com.xbaimiao.mirai.config.WebSocketBotConfig
import com.xbaimiao.mirai.event.GroupMessageEvent
import com.xbaimiao.mirai.eventbus.SubscribeHandler
import com.xbaimiao.mirai.eventbus.SubscribeListener
import com.xbaimiao.mirai.eventbus.SubscribePriority
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot

private lateinit var bot: WebSocketBot

fun main() {
    val webSocketBotConfig = WebSocketBotConfig("http://localhost:8080/", 780157773, "INITKEYezPvewbT")
    bot = WebSocketBot(webSocketBotConfig).connect()
    val friends = bot.getFriends()
    friends.thenAcceptAsync {
        for (friend in it) {
            println(friend.id)
        }
    }
    bot.join()
    bot.eventChancel.registerSubscribeListener(object : SubscribeListener {
        @SubscribeHandler(priority = SubscribePriority.LOWEST)
        fun groupMessageEvent(event: GroupMessageEvent) {
            println("1")
        }

        @SubscribeHandler(priority = SubscribePriority.NORMAL)
        fun groupMessageEvent3(event: GroupMessageEvent) {
            println("3")
            event.cancelled = true
            bot.eventChancel.unregisterSubscribeListener(this)

        }

        @SubscribeHandler(priority = SubscribePriority.HIGH, ignoreCancelled = true)
        fun groupMessageEvent2(event: GroupMessageEvent) {
            println("2")
        }
        @SubscribeHandler(priority = SubscribePriority.HIGHEST, ignoreCancelled = false)
        fun groupMessageEvent4(event: GroupMessageEvent) {
            println("4")
        }

    })
}


