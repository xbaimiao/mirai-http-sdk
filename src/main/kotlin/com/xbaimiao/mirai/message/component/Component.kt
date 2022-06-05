package com.xbaimiao.mirai.message.component

import com.xbaimiao.mirai.message.component.impl.*
import java.io.File
import java.net.URL

object Component {

    fun at(target: Long, display: String): BaseComponent {
        return At(target, display)
    }

    fun atAll(): BaseComponent {
        return AtAll()
    }

    fun text(string: String): BaseComponent {
        return PlainText(string)
    }

    fun image(url: URL): BaseComponent {
        return Image(url)
    }

    fun image(imageId: String): BaseComponent {
        return Image(imageId)
    }

    fun image(path: Image.Path): BaseComponent {
        return Image(path)
    }

    fun image(file: File): BaseComponent {
        return Image(file)
    }

    fun emoji(emojiId: Int, name: String): BaseComponent {
        return Emoji(emojiId, name)
    }

}