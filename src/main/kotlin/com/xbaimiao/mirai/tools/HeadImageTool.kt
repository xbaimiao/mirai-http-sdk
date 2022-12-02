package com.xbaimiao.mirai.tools

import java.net.URL

object HeadImageTool {
    /**
     * @return image base64
     * */
    @JvmStatic
    fun getHeadImage(qq: Long): String {
        val url = URL("https://q2.qlogo.cn/headimg_dl?dst_uin=${qq}&spec=100")
        return Base64ImageTool.getBase64Image(url)
    }
}