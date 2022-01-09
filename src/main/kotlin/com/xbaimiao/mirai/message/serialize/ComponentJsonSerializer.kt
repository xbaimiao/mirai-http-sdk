package com.xbaimiao.mirai.message.serialize

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.BaseComponent

interface ComponentJsonSerializer :
    MiraiSerializer<BaseComponent, JsonArray>,
    MiraiDeserializer<JsonArray, BaseComponent>,
    ComponentSerializer
