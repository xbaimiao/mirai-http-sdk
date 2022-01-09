package com.xbaimiao.mirai.message.serialize

import com.google.gson.JsonObject
import com.xbaimiao.mirai.message.component.BaseComponent

interface ComponentJsonSerializer :
    MiraiSerializer<BaseComponent, JsonObject>,
    MiraiDeserializer<BaseComponent, JsonObject>,
    ComponentSerializer
