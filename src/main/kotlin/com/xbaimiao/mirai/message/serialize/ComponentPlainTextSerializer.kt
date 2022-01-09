package com.xbaimiao.mirai.message.serialize

import com.xbaimiao.mirai.message.component.BaseComponent

interface ComponentPlainTextSerializer :
    MiraiSerializer<BaseComponent, String>,
    ComponentSerializer
