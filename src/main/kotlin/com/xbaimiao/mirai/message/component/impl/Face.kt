package com.xbaimiao.mirai.message.component.impl

import com.xbaimiao.mirai.message.component.AbstractComponent
import com.xbaimiao.mirai.message.component.BaseComponent
import com.xbaimiao.mirai.message.component.FaceComponent

class Face(
    override val faceId: Int, override val name: String,
    children: ArrayList<BaseComponent> = ArrayList(),
) : FaceComponent, AbstractComponent(children) {

    override fun fromChildren(children: List<BaseComponent>): BaseComponent = Face(faceId, name)

}