package com.xbaimiao.mirai.message.component.impl

import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.AtAllComponent
import com.xbaimiao.mirai.message.component.AtComponent
import com.xbaimiao.mirai.message.component.BaseComponent

class At(
    override val target: Long,
    override val display: String,
    children: ArrayList<BaseComponent> = ArrayList()
) : AtComponent, AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>): BaseComponent = At(target, display)

}

class AtAll(children: ArrayList<BaseComponent> = ArrayList()) : AtAllComponent, AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>): BaseComponent = AtAll()

}