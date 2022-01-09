package com.xbaimiao.mirai.message.serialize.message

import com.xbaimiao.mirai.message.Message
import com.xbaimiao.mirai.message.serialize.MiraiSerializer

interface MessagePlainTextSerializer : MiraiSerializer<Message, String>, MessageSerializer
