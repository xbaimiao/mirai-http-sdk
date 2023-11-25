package com.xbaimiao.mirai.message.component

import com.xbaimiao.mirai.message.component.collections.ComponentList
import com.xbaimiao.mirai.message.serialize.MiraiSerializer

@Suppress("UnnecessaryAbstractClass") // but this shouldn't be instantiated
abstract class AbstractComponent protected constructor(
    override val children: List<BaseComponent>
) : BaseComponent {

    override fun append(other: BaseComponent) = fromChildren(children + other)

    abstract fun fromChildren(children: List<BaseComponent>): BaseComponent

    override fun toList(): List<BaseComponent> =
        ComponentList(children.toMutableList().apply { add(0, fromChildren(emptyList())) })

    override operator fun plus(other: BaseComponent) = append(other)

    override fun toString() = "AbstractComponent(children=$children)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractComponent

        return children == other.children
    }

    override fun contentToString(): String {
        return MiraiSerializer.ComponentText.serialize(this)
    }

    override fun hashCode() = children.hashCode()
}
