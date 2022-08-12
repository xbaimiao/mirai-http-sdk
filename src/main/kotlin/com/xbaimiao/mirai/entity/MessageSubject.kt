package com.xbaimiao.mirai.entity

import com.google.gson.annotations.SerializedName

class MessageSubject {
    @SerializedName("id")
    var id = 0

    @SerializedName("kind")
    var kind: String? = null

    fun getKind(): SubjectKind{
        return SubjectKind.valueOf(kind!!)
    }

    enum class SubjectKind {
        Group, Friend
    }
}