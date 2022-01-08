package com.xbaimiao.mirai.entity

import com.xbaimiao.mirai.message.Message

interface Group {

    /**
     * 发送文本消息
     */
    fun reply(message: String)

    /**
     * 发送消息
     */
    fun reply(message: Message)

    /**
     * 群号
     */
    val id: Long

    /**
     * 群名称
     */
    val name: String

    /**
     * 机器人在此群的权限
     */
    val botPermission: BotPermission

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