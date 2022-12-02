package com.xbaimiao.mirai.tools

import java.io.File
import java.io.RandomAccessFile

object AmrAudioTool {
    @JvmStatic
    fun getAmrDuration(file: File): Long {
        var duration: Long = -1
        val packedSize = intArrayOf(
            12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0,
            0, 0
        )
        var randomAccessFile: RandomAccessFile? = null
        try {
            randomAccessFile = RandomAccessFile(file, "rw")
            val length = file.length() // 文件的长度
            var pos = 6 // 设置初始位置
            var frameCount = 0 // 初始帧数
            var packedPos = -1
            val datas = ByteArray(1) // 初始数据值
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
            duration += (frameCount * 20).toLong() // 帧数*20
        } finally {
            randomAccessFile?.close()
        }
        val x = (duration / 1000 + 1).toInt()
        return (3 * (x - 2) / 11).toLong() // 单位为秒
    }
}