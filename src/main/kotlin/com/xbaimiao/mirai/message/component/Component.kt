package com.xbaimiao.mirai.message.component

import com.xbaimiao.mirai.message.component.elements.AtTarget
import com.xbaimiao.mirai.message.component.impl.*
import java.net.URL

@Suppress("unused")
object Component {
    fun text(text: String): TextComponent = TextComponentImpl(text)
    fun image(url: URL): ImageComponent = ImageComponentImpl(url)
    fun at(target: AtTarget, display: String) = AtComponentImpl(target, display)
    fun at(target: Long, display: String) = AtComponentImpl(AtTarget.single(target), display)
    fun emoji(emojiId: Int, name: String) = EmojiComponentImpl(emojiId, name)
}
