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
    MUSIC("MusicShare"),
    QUOTE("Quote"),
    POKE("Poke"),
    DICE("Dice"),
    JSON("Json"),
    MIRAI("MiraiCode"),
    FORWARD_MESSAGE("Forward"),
    NULL("null");

    companion object {
        fun formKey(string: String): ComponentType {
            return values().firstOrNull { it.key == string } ?: NULL
        }
    }
}