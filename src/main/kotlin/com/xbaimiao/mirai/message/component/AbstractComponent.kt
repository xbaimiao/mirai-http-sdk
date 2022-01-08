package com.xbaimiao.mirai.message.component

@Suppress("UnnecessaryAbstractClass") // but this shouldn't be instantiated
abstract class AbstractComponent protected constructor(
    override val children: List<BaseComponent>
) : BaseComponent {

    override fun append(other: BaseComponent) = fromChildren(children + other)

    abstract fun fromChildren(children: List<BaseComponent>): BaseComponent

    override operator fun plus(other: BaseComponent) = append(other)

    override fun toString() = "AbstractComponent(children=$children)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractComponent

        if (children != other.children) return false

        return true
    }

    override fun hashCode() = children.hashCode()
}
