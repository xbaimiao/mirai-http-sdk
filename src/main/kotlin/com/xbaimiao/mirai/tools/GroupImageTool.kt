package com.xbaimiao.mirai.tools

import java.net.URL

object GroupImageTool {
    /**
     * @return image base64
     * */
    @JvmStatic
    fun getGroupImage(group: Long): String {
        val url = URL("http://p.qlogo.cn/gh/${group}/${group}/100/")
        return Base64ImageTool.getBase64Image(url)
    }
}