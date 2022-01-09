package com.xbaimiao.mirai.message.component

import com.xbaimiao.mirai.message.component.impl.*
import java.net.URL

@Suppress("unused")
object Component {
    fun text(text: String): TextComponent = TextComponentImpl(text)
    fun image(url: URL): ImageComponent = ImageComponentImpl(url)
    fun at(target: Long, display: String) = At(target, display)
    fun atAll() = AtAll()
    fun face(faceId: Int, name: String) = Face(faceId, name)
}
