package com.xbaimiao.mirai.message.serialize

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.Message

interface MessageJsonSerializer :
    MiraiSerializer<Message, JsonObject>,
    MiraiDeserializer<JsonObject, Message>,
    MessageSerializer
