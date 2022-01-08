package com.xbaimiao.mirai

import com.xbaimiao.mirai.config.SDKConfig
import com.xbaimiao.mirai.message.component.ImageComponent
import com.xbaimiao.mirai.message.component.TextComponent
import com.xbaimiao.mirai.packet.impl.group.GroupListPacket
import com.xbaimiao.mirai.packet.impl.websocket.BindWSPacket

object MiraiHttpSDK {

    lateinit var sdkConfig: SDKConfig
    lateinit var bindWS: BindWSPacket

    @JvmStatic
    fun main(args: Array<String>) {
        sdkConfig = SDKConfig("http://localhost:8080/", 2157207381, "INITKEY4f2jsadasVMXq")
        init()
    }

    fun init() {
        /**
         * 认证
         */
        bindWS = BindWSPacket(sdkConfig).read<BindWSPacket>()
        GroupListPacket(bindWS.session).read<GroupListPacket>().apply {
            for (group in this.groups) {
                if (group.id == 451555371L) {
                    group.reply(TextComponent("打撒").append(ImageComponent("https://img-blog.csdn.net/20180912194838397?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3h1ZmVpNTc4OTY1MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70")))
                }
                println(group.toString())
            }
        }
//        authPacket = AuthPacket().read() as AuthPacket
//        println(authPacket.toString())
//        BindPacket(sdkConfig.qq, authPacket.session).sendAsync {
//            println(this.body())
//        }
//        GroupListPacket().sendAsync {
//            println(this.body())
//        }
        join()
    }

    fun join() {
        while (true) {
            Thread.sleep(10000)
        }
    }

}