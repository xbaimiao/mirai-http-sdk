package com.xbaimiao.mirai.message

import com.google.gson.annotations.SerializedName

data class MessageSource(
    @SerializedName("id")
    val messageId: Long,
    @SerializedName("time")
    val time: Long
)