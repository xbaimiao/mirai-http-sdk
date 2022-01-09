package com.xbaimiao.mirai

import com.xbaimiao.mirai.config.WebSocketBotConfig
import com.xbaimiao.mirai.event.group.GroupMessageEvent
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot
import org.greenrobot.eventbus.Subscribe

object MiraiHttpSDK {

    lateinit var webSocketBotConfig: WebSocketBotConfig
    lateinit var bot: WebSocketBot

    @JvmStatic
    fun main(args: Array<String>) {
        webSocketBotConfig = WebSocketBotConfig("http://localhost:8080/", 2157207381, "INITKEY4f2jsadasVMXq")
        init()
        bot.eventChancel.registerListener(this)
    }

    @Subscribe
    fun a(event: GroupMessageEvent) {
        println(event.plainText)
    }

    fun init() {
        bot = WebSocketBot(webSocketBotConfig).connect()
        bot.join()
        Component.text("大武当挖的安慰安慰") + Component.text("大武当挖的安慰安慰")
        bot.getGroups().thenApply {
            for (group in it) {
                if (group.id == 418888134L) {
                    group.getMembers().thenAcceptAsync { members ->
                        for (friend in members) {
                            if (friend.id == 3104026189) {
                                println(1)
                                try {
                                    friend.sendMessage(Component.text("大武当挖的安慰安慰") + Component.text("大武当挖的安慰安慰"))
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}