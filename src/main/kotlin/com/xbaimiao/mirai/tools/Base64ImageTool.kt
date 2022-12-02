package com.xbaimiao.mirai.tools

import java.net.URL
import java.util.*

object Base64ImageTool {
    @JvmStatic
    fun getBase64Image(url: URL): String {
        val connection = url.openConnection()
        connection.connectTimeout = 1000
        val inputStream = connection.getInputStream()
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes)
        inputStream.close()
        return Base64.getEncoder().encodeToString(bytes)
    }
}