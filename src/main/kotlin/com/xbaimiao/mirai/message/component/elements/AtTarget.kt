package com.xbaimiao.mirai.message.component.elements

import com.xbaimiao.mirai.message.component.elements.impl.SingleAtTargetImpl
import com.xbaimiao.mirai.message.component.elements.impl.WideAtTargetImpl

interface AtTarget {

    companion object {
        val all: AtTarget = WideAtTargetImpl
        fun single(target: Long): AtTarget = SingleAtTargetImpl(target)
    }
}
