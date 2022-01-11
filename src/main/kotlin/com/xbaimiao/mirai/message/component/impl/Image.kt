package com.xbaimiao.mirai.message.component.impl

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.packet.enums.ComponentType
import java.io.File
import java.io.FileInputStream
import java.net.URL
import java.util.*

class Image(
    val jsonObject: JsonObject,
    children: List<BaseComponent> = ComponentList()
) : AbstractComponent(children) {

    init {
        jsonObject.addProperty("type", ComponentType.IMAGE.key)
    }

    constructor(url: URL) : this(JsonObject().apply {
        addProperty("url", url.toString().also { println(it) })
    })

    constructor(file: File) : this(JsonObject().apply {
        addProperty("base64", imageToBase64(file))
    })

    constructor(path: Path) : this(JsonObject().apply {
        addProperty("path", path.path)
    })

    constructor(imageId: String) : this(JsonObject().apply {
        addProperty("imageId", imageId)
    })

    fun queryUrl(): String? {
        return jsonObject.get("url").asString
    }

    fun getBase64(): String? {
        if (!jsonObject.has("base64")) {
            return null
        }
        return if (jsonObject.get("base64").isJsonNull) {
            null
        } else {
            jsonObject.get("base64").asString
        }
    }

    fun getPath(): String? {
        if (!jsonObject.has("path")) {
            return null
        }
        return if (jsonObject.get("path").isJsonNull) {
            null
        } else {
            jsonObject.get("path").asString
        }
    }

    fun getImageId(): String? {
        return jsonObject.get("imageId").asString
    }

    override fun fromChildren(children: List<BaseComponent>) = Image(jsonObject, children)

    override fun serializeToJson(): JsonObject {
        return jsonObject
    }

    override fun serializeToPlainText(): String {
        return "[图片]"
    }

    class Path(val path: String)

    companion object {
        private fun imageToBase64(file: File): String {
            val inputStream = FileInputStream(file)
            val byteArray = ByteArray(inputStream.available())
            inputStream.read(byteArray)
            inputStream.close()
            return String(Base64.getEncoder().encode(byteArray))
        }
    }

}
