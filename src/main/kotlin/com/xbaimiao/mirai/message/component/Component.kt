package com.xbaimiao.mirai.message.component

import com.xbaimiao.mirai.message.component.impl.ImageComponentImpl
import com.xbaimiao.mirai.message.component.impl.TextComponentImpl
import java.net.URL

@Suppress("unused")
object Component {
    fun text(text: String): TextComponent = TextComponentImpl(text)
    fun image(url: URL): ImageComponent = ImageComponentImpl(url)
}
