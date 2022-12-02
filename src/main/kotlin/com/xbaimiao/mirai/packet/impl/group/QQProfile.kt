package com.xbaimiao.mirai.packet.impl.group

import com.google.gson.annotations.SerializedName
import com.xbaimiao.mirai.packet.enums.SexType

data class QQProfile(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("level")
    val level: Long,
    @SerializedName("sign")
    val sign: String,
    @SerializedName("sex")
    val sexstr: String
) {
    val sex_type: SexType = SexType.valueOf(sexstr)
}
