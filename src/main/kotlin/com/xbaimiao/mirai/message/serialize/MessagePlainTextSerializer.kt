package com.xbaimiao.mirai.message.serialize

import com.xbaimiao.mirai.message.Message

interface MessagePlainTextSerializer : MiraiSerializer<Message, String>, MessageSerializer
