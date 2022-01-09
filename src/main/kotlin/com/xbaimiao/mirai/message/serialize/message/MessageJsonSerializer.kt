package com.xbaimiao.mirai.message.serialize.message

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.serialize.MiraiDeserializer
import com.xbaimiao.mirai.message.serialize.MiraiSerializer

interface MessageJsonSerializer :
    MiraiSerializer<Message, JsonObject>,
    MiraiDeserializer<JsonObject, Message>,
    MessageSerializer
