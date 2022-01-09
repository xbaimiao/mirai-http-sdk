//package com.xbaimiao.mirai.packet.impl.group
//
//import com.google.gson.Gson
//import com.google.gson.JsonParser
//import com.xbaimiao.mirai.entity.Friend
//import com.xbaimiao.mirai.packet.Packet
//import com.xbaimiao.mirai.packet.enums.HttpMethod
//import java.io.StringReader
//
//class FriendListPacket(private val session: String) : Packet() {
//
//    val friends = ArrayList<Friend>()
//
//    override val httpMethod = HttpMethod.GET
//    override val targetedPath = "friendList?sessionKey=${session}"
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : Packet> read(): T {
//        this.send {
//            val body = this.body()
//            val jsonObject = JsonParser.parseReader(StringReader(body)).asJsonObject
//            if (jsonObject.get("code").asInt != 0) {
//                return@send
//            }
//            jsonObject.get("data").asJsonArray.forEach {
//                it.asJsonObject.apply {
//                    friends.add(Gson().fromJson(this.toString(), Friend::class.java))
//                }
//            }
//        }
//        return this as T
//    }
//
//}