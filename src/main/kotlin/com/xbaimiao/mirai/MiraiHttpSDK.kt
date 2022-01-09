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
            try {
                event.reply(Component.atAll())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        println("群{${event.group.name}(${event.group.id})} -> ${event.sender.nickName}(${event.sender.id}): ${event.plainText}")
    }

    fun init() {
        bot = WebSocketBot(webSocketBotConfig).connect()
        bot.join()
    }
}