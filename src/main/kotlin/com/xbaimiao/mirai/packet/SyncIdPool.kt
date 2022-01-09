package com.xbaimiao.mirai.packet

/**
 * syncId池
 */
object SyncIdPool {

    private var id = 0L

    fun next(): Long {
        return id++
    }

}