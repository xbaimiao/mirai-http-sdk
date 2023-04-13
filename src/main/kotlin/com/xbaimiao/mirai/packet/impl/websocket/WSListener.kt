package com.xbaimiao.mirai.packet.impl.websocket

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.xbaimiao.mirai.entity.Friend
import com.xbaimiao.mirai.entity.Group
import com.xbaimiao.mirai.entity.MemberFriend
import com.xbaimiao.mirai.entity.MessageSubject
import com.xbaimiao.mirai.event.*
import com.xbaimiao.mirai.eventbus.EventChancel
import com.xbaimiao.mirai.message.MessageSource
import com.xbaimiao.mirai.message.serialize.MiraiSerializer
import com.xbaimiao.mirai.packet.CommandPacket
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft_6455
import org.java_websocket.handshake.ServerHandshake
import java.io.StringReader
import java.net.URI
import java.util.concurrent.CompletableFuture

/**
 * 监听器
 */
class WSListener(private val bot: WebSocketBot, serverUri: URI?) :
    WebSocketClient(serverUri, Draft_6455(), null, 1000) {

    val putPackets = HashMap<Long, CommandPacket<*>>()
    var buffer = StringBuilder()
    private val accumulatedMessage = CompletableFuture<Any>()
    internal var on = true

    override fun onOpen(handshakedata: ServerHandshake?) {
        bot.getGroups()
        bot.getFriends()
    }

    override fun onClose(statusCode: Int, reason: String?, remote: Boolean) {
        bot.closeFunc.forEach { it.invoke(bot) }
    }

    override fun onError(ex: java.lang.Exception?) {
        ex!!.printStackTrace()
    }

    override fun onMessage(message: String?) {
        if (!on) {
            return
        }
        buffer.append(message)
        val text = buffer.toString()
        buffer = StringBuilder()
        try {
            val jsonObject = JsonParser.parseReader(StringReader(text)).asJsonObject
            if (!jsonObject.has("syncId")) {
                return
            }
            val syncId = jsonObject.get("syncId").asString
            if (syncId == "-1") {
                //消息
                val data = jsonObject.getAsJsonObject("data")
                when (data.get("type").asString) {
                    "GroupMessage" -> {
                        val memberFriend =
                            Gson().fromJson(data.get("sender").asJsonObject, MemberFriend::class.java)
                        val groupMessageEvent = GroupMessageEvent(
                            memberFriend.group,
                            memberFriend,
                            data.get("messageChain").asJsonArray.toSource(),
                            MiraiSerializer.ComponentSerializer.deserialize(data.get("messageChain").asJsonArray)
                        )
                        EventChancel.call(groupMessageEvent)
                    }

                    "TempMessage" -> {
                        val memberFriend =
                            Gson().fromJson(data.get("sender").asJsonObject, MemberFriend::class.java)
                        val groupMessageEvent = GroupTempMessageEvent(
                            memberFriend.group,
                            memberFriend,
                            data.get("messageChain").asJsonArray.toSource(),
                            MiraiSerializer.ComponentSerializer.deserialize(data.get("messageChain").asJsonArray)
                        )
                        EventChancel.call(groupMessageEvent)
                    }

                    "FriendMessage" -> {
                        val friend = Gson().fromJson(data.get("sender").asJsonObject, Friend::class.java)
                        val friendMessageEvent = FriendMessageEvent(
                            friend,
                            data.get("messageChain").asJsonArray.toSource(),
                            MiraiSerializer.ComponentSerializer.deserialize(data.get("messageChain").asJsonArray)
                        )
                        EventChancel.call(friendMessageEvent)
                    }

                    "BotGroupPermissionChangeEvent" -> {
                        val groupPermissionEvent = GroupPermissionChangeEvent(
                            Gson().fromJson(data.get("group").asJsonObject, Group::class.java),
                            data.get("origin").asString,
                            data.get("current").asString
                        )
                        EventChancel.call(groupPermissionEvent)
                    }

                    "BotMuteEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        val botMuteEvent = BotMuteEvent(
                            member.group,
                            data.get("durationSeconds").asInt,
                            member
                        )
                        EventChancel.call(botMuteEvent)
                    }

                    "BotUnmuteEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        val botUnMuteEvent = BotUnMuteEvent(
                            member.group,
                            member
                        )
                        EventChancel.call(botUnMuteEvent)
                    }

                    "BotJoinGroupEvent" -> {
                        val botJoinGroupEvent: BotJoinGroupEvent
                        if (data.get("invitor").isJsonNull) {
                            botJoinGroupEvent = BotJoinGroupEvent(
                                Gson().fromJson(data.get("group").asJsonObject, Group::class.java),
                                null
                            )
                        } else {
                            val member: MemberFriend =
                                Gson().fromJson(data.get("invitor").asJsonObject, MemberFriend::class.java)
                            botJoinGroupEvent = BotJoinGroupEvent(
                                Gson().fromJson(data.get("group").asJsonObject, Group::class.java),
                                member
                            )
                        }
                        EventChancel.call(botJoinGroupEvent)
                    }

                    "BotLeaveEventActive" -> {
                        val botLeaveEventActive = BotLeaveEventActive(
                            Gson().fromJson(data.get("group").asJsonObject, Group::class.java)
                        )
                        EventChancel.call(botLeaveEventActive)
                    }

                    "BotLeaveEventKick" -> {
                        val botLeaveEventKick = BotLeaveEventKick(
                            Gson().fromJson(data.get("group").asJsonObject, Group::class.java),
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        )
                        EventChancel.call(botLeaveEventKick)
                    }

                    "BotLeaveEventDisband" -> {
                        val botLeaveEventDisband = BotLeaveEventDisband(
                            Gson().fromJson(data.get("group").asJsonObject, Group::class.java),
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        )
                        EventChancel.call(botLeaveEventDisband)
                    }

                    "GroupRecallEvent" -> {
                        val groupRecallEvent = GroupRecallEvent(
                            Gson().fromJson(data.get("group").asJsonObject, Group::class.java),
                            data.get("authorId").asLong,
                            data.get("messageId").asInt,
                            data.get("time").asInt,
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        )
                        EventChancel.call(groupRecallEvent)
                    }

                    "FriendRecallEvent" -> {
                        val friendRecallEvent = FriendRecallEvent(
                            data.get("authorId").asLong,
                            data.get("messageId").asInt,
                            data.get("time").asInt,
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        )
                        EventChancel.call(friendRecallEvent)
                    }

                    "NudgeEvent" -> {
                        val nudgeEvent = NudgeEvent(
                            data.get("fromId").asLong,
                            Gson().fromJson(data.get("subject").asJsonObject, MessageSubject::class.java),
                            data.get("action").asString,
                            data.get("suffix").asString,
                            data.get("target").asLong
                        )
                        EventChancel.call(nudgeEvent)
                    }

                    "GroupNameChangeEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        val groupNameChangeEvent = GroupNameChangeEvent(
                            member.group,
                            member,
                            data.get("origin").asString,
                            data.get("current").asString
                        )
                        EventChancel.call(groupNameChangeEvent)
                    }

                    "GroupEntranceAnnouncementChangeEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        val groupEntranceAnnouncementChangeEvent = GroupEntranceAnnouncementChangeEvent(
                            member.group,
                            member,
                            data.get("origin").asString,
                            data.get("current").asString
                        )
                        EventChancel.call(groupEntranceAnnouncementChangeEvent)
                    }

                    "GroupMuteAllEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        val groupMuteAllEvent = GroupMuteAllEvent(
                            member.group,
                            member,
                            data.get("origin").asBoolean,
                            data.get("current").asBoolean
                        )
                        EventChancel.call(groupMuteAllEvent)
                    }

                    "GroupAllowAnonymousChatEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        val groupAllowAnonymousChatEvent = GroupAllowAnonymousChatEvent(
                            member.group,
                            member,
                            data.get("origin").asBoolean,
                            data.get("current").asBoolean
                        )
                        EventChancel.call(groupAllowAnonymousChatEvent)
                    }

                    "GroupAllowConfessTalkEvent" -> {
                        val groupAllowConfessTalkEvent = GroupAllowConfessTalkEvent(
                            Gson().fromJson(data.get("group").asJsonObject, Group::class.java),
                            data.get("origin").asBoolean,
                            data.get("current").asBoolean,
                            data.get("isByBot").asBoolean
                        )
                        EventChancel.call(groupAllowConfessTalkEvent)
                    }

                    "GroupAllowMemberInviteEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        val groupAllowMemberInviteEvent = GroupAllowMemberInviteEvent(
                            member.group,
                            member,
                            data.get("origin").asBoolean,
                            data.get("current").asBoolean
                        )
                        EventChancel.call(groupAllowMemberInviteEvent)
                    }

                    "MemberJoinEvent" -> {
                        val memberJoinEvent: MemberJoinEvent
                        if (data.get("invitor").isJsonNull) {
                            val member: MemberFriend =
                                Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java)
                            memberJoinEvent = MemberJoinEvent(
                                member.group,
                                member,
                                null
                            )
                        } else {
                            val member: MemberFriend =
                                Gson().fromJson(data.get("invitor").asJsonObject, MemberFriend::class.java)
                            memberJoinEvent = MemberJoinEvent(
                                Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java).group,
                                Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java),
                                member
                            )
                        }
                        EventChancel.call(memberJoinEvent)
                    }

                    "MemberLeaveEventKick" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java)
                        val MemberLeaveEventKick = MemberLeaveEventKick(
                            Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java),
                            member
                        )
                        EventChancel.call(MemberLeaveEventKick)
                    }

                    "MemberLeaveEventQuit" -> {
                        val MemberLeaveEventQuit = MemberLeaveEventQuit(
                            Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java)
                        )
                        EventChancel.call(MemberLeaveEventQuit)
                    }

                    "MemberCardChangeEvent" -> {
                        val MemberCardChangeEvent = MemberCardChangeEvent(
                            Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java),
                            data.get("origin").asString,
                            data.get("current").asString
                        )
                        EventChancel.call(MemberCardChangeEvent)
                    }

                    "MemberPermissionChangeEvent" -> {
                        val MemberPermissionChangeEvent = MemberPermissionChangeEvent(
                            Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java),
                            data.get("origin").asString,
                            data.get("current").asString
                        )
                        EventChancel.call(MemberPermissionChangeEvent)
                    }

                    "MemberMuteEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java)
                        val memberMuteEvent = MemberMuteEvent(
                            data.get("durationSeconds").asInt,
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java),
                            member
                        )
                        EventChancel.call(memberMuteEvent)
                    }

                    "MemberUnmuteEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java)
                        val memberUnMuteEvent = MemberUnMuteEvent(
                            Gson().fromJson(data.get("operator").asJsonObject, MemberFriend::class.java),
                            member
                        )
                        EventChancel.call(memberUnMuteEvent)
                    }

                    "MemberHonorChangeEvent" -> {
                        val member: MemberFriend =
                            Gson().fromJson(data.get("member").asJsonObject, MemberFriend::class.java)
                        val MemberHonorChangeEvent = MemberHonorChangeEvent(
                            member,
                            data.get("action").asString,
                            data.get("honor").asString
                        )
                        EventChancel.call(MemberHonorChangeEvent)
                    }

                    "NewFriendRequestEvent" -> {
                        val NewFriendRequestEvent = NewFriendRequestEvent(
                            data.get("eventId").asLong,
                            data.get("fromId").asLong,
                            data.get("groupId").asLong,
                            data.get("nick").asString,
                            data.get("message").asString
                        )
                        EventChancel.call(NewFriendRequestEvent)
                    }

                    "MemberJoinRequestEvent" -> {
                        val MemberJoinRequestEvent = MemberJoinRequestEvent(
                            data.get("eventId").asLong,
                            data.get("fromId").asLong,
                            data.get("groupId").asLong,
                            data.get("groupName").asString,
                            data.get("nick").asString,
                            data.get("message").asString
                        )
                        EventChancel.call(MemberJoinRequestEvent)
                    }

                    "BotInvitedJoinGroupRequestEvent" -> {
                        val BotInvitedJoinGroupRequestEvent = BotInvitedJoinGroupRequestEvent(
                            data.get("eventId").asLong,
                            data.get("fromId").asLong,
                            data.get("groupId").asLong,
                            data.get("groupName").asString,
                            data.get("nick").asString,
                            data.get("message").asString
                        )
                        EventChancel.call(BotInvitedJoinGroupRequestEvent)
                    }

                }
                return
            }
            if (syncId == "") {
                val data = jsonObject.getAsJsonObject("data")
                if (data.get("code").asInt == 0 && data.has("session")) {
                    sessionKey = data.get("session").asString
                }
                //推送消息
                return
            }
            putPackets.keys.forEach {
                if (syncId.toLong() == it) {
                    putPackets[it]!!.put(jsonObject.toString())
                }
            }
            putPackets.remove(syncId.toLong())
        } catch (e: Exception) {
            println("input \"$text\"")
            e.printStackTrace()
        }
        return
    }

    private fun JsonArray.toSource(): MessageSource {
        for (it in this) {
            if (it.asJsonObject.get("type").asString == "Source") {
                return Gson().fromJson(it, MessageSource::class.java)
            }
        }
        return MessageSource(-1, -1)
    }

    var sessionKey: String = ""

}