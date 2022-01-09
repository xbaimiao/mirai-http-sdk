package com.xbaimiao.mirai.message.serialize.component

import com.google.gson.JsonArray
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.serialize.MiraiDeserializer
import com.xbaimiao.mirai.message.serialize.MiraiSerializer

interface ComponentJsonSerializer :
    MiraiSerializer<BaseComponent, JsonArray>,
    MiraiDeserializer<JsonArray, BaseComponent>,
    ComponentSerializer
