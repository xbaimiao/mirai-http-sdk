package com.xbaimiao.mirai

import com.xbaimiao.mirai.config.WebSocketBotConfig
import com.xbaimiao.mirai.event.GroupMessageEvent
import com.xbaimiao.mirai.message.component.impl.At
import com.xbaimiao.mirai.message.component.impl.Image
import com.xbaimiao.mirai.packet.impl.websocket.WebSocketBot
import org.greenrobot.eventbus.Subscribe
import java.util.*

object MiraiHttpSDK {

    lateinit var webSocketBotConfig: WebSocketBotConfig
    lateinit var bot: WebSocketBot

    @JvmStatic
    fun main(args: Array<String>) {
        webSocketBotConfig = WebSocketBotConfig("http://localhost:8080/", 2157207381, "INITKEY4f2jsadasVMXq")
        bot = WebSocketBot(webSocketBotConfig).connect()
        bot.join()
        bot.eventChancel.registerListener(this)
    }

    @Subscribe
    fun a(event: GroupMessageEvent) {
        if (event.group.id == 451555371L) {
            for (baseComponent in event.component.toList()) {
                if (baseComponent is Image) {
                    println(baseComponent.queryUrl())
                    println(baseComponent.getBase64())
                    println(baseComponent.getPath())
                    println(baseComponent.getImageId())
                }
                if (baseComponent is At) {
                    println(baseComponent.target)
                    println(baseComponent.display)
                }

            }
//            println("收到消息")
//            event.reply(PlainText("dasdas") + Image(File("C:\\Users\\31040\\Pictures\\97073.jpg")))
        }
//        println("群{${event.group.name}(${event.group.id})} -> ${event.sender.nickName}(${event.sender.id}): ${event.plainText}")
    }

}