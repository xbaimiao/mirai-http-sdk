package com.xbaimiao.mirai.entity

import java.util.NoSuchElementException

interface MiraiNumberIdentifiable<T> {
    val id: Long
}

interface MiraiNumberQueryable<T : MiraiNumberIdentifiable<*>> {
    @Throws(NoSuchElementException::class)
    fun fromId(id: Long): T

    fun fromIdOrNull(id: Long): T?
}
