package com.xbaimiao.mirai.message.serialize.component

import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.serialize.MiraiSerializer

interface ComponentPlainTextSerializer :
    MiraiSerializer<BaseComponent, String>,
    ComponentSerializer
