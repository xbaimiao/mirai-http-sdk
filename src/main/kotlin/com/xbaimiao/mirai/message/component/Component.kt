package com.xbaimiao.mirai.message.component

abstract class Component : BaseComponent {

    private val components = ArrayList<BaseComponent>()
    private var isAddSelf = false

    fun append(component: Component): Component {
        if (this is TextComponent && component is TextComponent) {
            this.string = this.string + component.string
            return this
        }
        if (!isAddSelf) {
            components.add(this)
            isAddSelf = false
        }
        components.add(component)
        return this
    }

    fun getComponents(): List<BaseComponent> {
        if (!isAddSelf) {
            components.add(this)
            isAddSelf = false
        }
        return components
    }

}

class TextComponent(var string: String) : Component()

class ImageComponent(var url: String) : Component()