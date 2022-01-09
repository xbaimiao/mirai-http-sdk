package com.xbaimiao.mirai.packet.enums

enum class ComponentType(
    val key: String
) {
    SOURCE("Source"),
    PLAIN("Plain"),
    IMAGE("Image"),
    AT("At"),
    AT_ALL("AtAll"),
    FACE("Face"),
    FLASH_IMAGE("FlashImage"),
    VOICE("Voice"),
    NULL("null");

    companion object {
        fun formKey(string: String): ComponentType {
            return values().firstOrNull { it.key == string } ?: NULL
        }
    }
}