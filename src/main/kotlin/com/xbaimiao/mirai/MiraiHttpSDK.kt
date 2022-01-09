package com.xbaimiao.mirai

import com.xbaimiao.mirai.config.SDKConfig
import com.xbaimiao.mirai.message.component.Component
import com.xbaimiao.mirai.packet.SyncIdPool
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
        bindWS = BindWSPacket(sdkConfig).connect()
        Thread.sleep(1000)
//        FriendListPacket(bindWS.session).read<FriendListPacket>().apply {
//            for (friend in this.friends) {
//                println(friend.toString())
//            }
//        }
        Thread{
            GroupListPacket().send().thenAcceptAsync {
                println(SyncIdPool.next())
                for (group in it.groups) {
                    if (group.id == 451555371L) {
                        group.sendMessage(Component.text("打撒") + Component.text("达到"))
                    }
                    println(group.toString())
                }
            }
        }.start()
        join()
    }

    fun join() {
        while (true) {
            Thread.sleep(10000)
        }
    }

}