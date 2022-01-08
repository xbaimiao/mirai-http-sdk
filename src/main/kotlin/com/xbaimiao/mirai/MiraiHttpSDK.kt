package com.xbaimiao.mirai

import com.xbaimiao.mirai.config.SDKConfig
import com.xbaimiao.mirai.packet.impl.GroupListPacket
import com.xbaimiao.mirai.packet.impl.websocket.BindWSPacket

object MiraiHttpSDK {

    lateinit var sdkConfig: SDKConfig

    @JvmStatic
    fun main(args: Array<String>) {
        sdkConfig = SDKConfig("http://localhost:8080/", 2157207381, "INITKEY4f2jsadasVMXq")
        init()
    }

    fun init() {
        /**
         * 认证
         */
        val bindWS = BindWSPacket(sdkConfig).read<BindWSPacket>()
        GroupListPacket(bindWS.session).read<GroupListPacket>().apply {
            for (group in this.groups) {
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