package com.xbaimiao.mirai.entity

import com.google.gson.Gson
import com.xbaimiao.mirai.message.Message

class GroupImpl(
    override val name: String,
    override val id: Long,
    override val botPermission: Group.BotPermission
) : Group {

    override fun reply(message: Message) {
        TODO("Not yet implemented")
    }

    override fun reply(message: String) {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}