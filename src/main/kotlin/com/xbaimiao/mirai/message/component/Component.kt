package com.xbaimiao.mirai.message.component

abstract class Component : BaseComponent {

    val list = ArrayList<BaseComponent>()

    fun append(baseComponent: BaseComponent) {
        if (this is TextComponent && baseComponent is TextComponent) {
            this.string = this.string + baseComponent.string
            return
        }
        list.add(baseComponent)
    }

}

class TextComponent(var string: String) : Component()

class ImageComponent(var url: String) : Component()