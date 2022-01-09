package com.xbaimiao.mirai.entity

interface MiraiNumberIdentifiable<T : Number> {
    val id: T
}

interface MiraiNumberQueryable<T : MiraiNumberIdentifiable<*>> {
    @Throws(NoSuchElementException::class)
    fun fromId(id: Long): T

    fun fromIdOrNull(id: Long): T?
}
