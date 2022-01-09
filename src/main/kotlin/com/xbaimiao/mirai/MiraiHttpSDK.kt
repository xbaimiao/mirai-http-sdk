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
        if (event.plainText == "牛逼不"){
            event.reply("牛逼牛逼")
        }
        println("群消息${event.group.id} -> ${event.sender.nickName}(${event.sender.id}): ${event.plainText}")
    }

    fun init() {
        bot = WebSocketBot(webSocketBotConfig).connect()
        bot.join()
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