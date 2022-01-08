package com.xbaimiao.mirai.entity.group

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.entity.MiraiMessageTransmittable
import com.xbaimiao.mirai.message.Message

class Group(
    /**
     * 群名称
     */
    @SerializedName("name")
    val name: String,
    /**
     * 群号
     */
    @SerializedName("id")
    val id: Long,
    /**
     * 机器人在此群的权限
     */
    @SerializedName("permission")
    val botPermission: BotPermission
) : MiraiMessageTransmittable {

    override fun reply(message: Message) {
        TODO("Not yet implemented")
    }

    override fun reply(message: String) {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

    enum class BotPermission {
        /**
         * 成员
         */
        MEMBER,

        /**
         * 群主
         */
        OWNER,

        /**
         * 管理员
         */
        ADMINISTRATOR
    }

}