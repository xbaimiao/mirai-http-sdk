package com.xbaimiao.mirai.message.component

import com.xbaimiao.mirai.message.component.elements.AtTarget
import java.net.URL

interface TextComponent : BaseComponent {
    val string: String
}

interface ImageComponent : BaseComponent {
    val url: URL
}

interface AtComponent : BaseComponent {
    val target: AtTarget
    val display: String
}

interface EmojiComponent : BaseComponent {
    val emojiId: Int
    val name: String
}
