package com.xbaimiao.mirai.entity

import com.google.gson.annotations.SerializedName

class MemberActive(
    @SerializedName("temperature")
    val temperature: Int,
    @SerializedName("point")
    val point: Int,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("honors")
    val honors: MutableList<String>
)