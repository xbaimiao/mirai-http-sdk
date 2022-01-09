package com.xbaimiao.mirai.message.component

import java.net.URL

interface TextComponent : BaseComponent {
    val string: String
}

interface ImageComponent : BaseComponent {
    val url: URL
}

interface AtComponent : AtAllComponent {
    val target: Long
    val display: String
}

interface AtAllComponent : BaseComponent

interface FaceComponent : BaseComponent {
    val faceId: Int
    val name: String
}