package com.xbaimiao.mirai.message.component.collections

import com.xbaimiao.mirai.message.component.BaseComponent

class ComponentList(
    private val delegate: List<BaseComponent>
) : Iterable<BaseComponent>, List<BaseComponent> by delegate {

    constructor() : this(emptyList())

    override fun iterator() = ComponentIterator()

    inner class ComponentIterator : Iterator<BaseComponent> {

        private val delegateIterator = delegate.iterator()
        private var subIterator: Iterator<BaseComponent>? = null

        override fun hasNext() =
            delegateIterator.hasNext() || subIterator?.hasNext() == true

        @Suppress("ReturnCount")
        override fun next(): BaseComponent {
            if (!hasNext()) throw NoSuchElementException()

            if (subIterator?.hasNext() == true) return subIterator!!.next()
            val next = delegateIterator.next()
            subIterator = next.children.iterator()

            return next
        }
    }
}
