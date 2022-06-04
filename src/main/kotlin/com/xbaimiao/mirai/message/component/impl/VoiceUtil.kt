package com.xbaimiao.mirai.message.component.impl

import java.io.File
import kotlin.Throws
import java.io.IOException
import java.io.RandomAccessFile

object VoiceUtil {
    @Throws(IOException::class)
    fun getAmrDuration(file: File): Long {
        var duration: Long = -1
        val packedSize = intArrayOf(12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0, 0, 0)
        var randomAccessFile: RandomAccessFile? = null
        try {
            randomAccessFile = RandomAccessFile(file, "rw")
            val length = file.length()
            var pos = 6
            var frameCount = 0
            var packedPos = -1
            val datas = ByteArray(1)
            while (pos <= length) {
                randomAccessFile.seek(pos.toLong())
                if (randomAccessFile.read(datas, 0, 1) != 1) {
                    duration = if (length > 0) (length - 6) / 650 else 0
                    break
                }
                packedPos = datas[0].toInt() shr 3 and 0x0F
                pos += packedSize[packedPos] + 1
                frameCount++
            }
            duration += frameCount * 20L
        } finally {
            randomAccessFile?.close()
        }
        val x = (duration / 1000 + 1).toInt()
        return 3L * (x - 2) / 11
    }
}