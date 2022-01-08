package com.xbaimiao.mirai.message.component

import java.net.URL

interface TextComponent : BaseComponent {
    val string: String
}

interface ImageComponent : BaseComponent {
    val url: URL
}
