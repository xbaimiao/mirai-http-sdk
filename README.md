# [Mirai-Http-SDK](README.md)
- 一个Mirai-HTTP-API封装接口

## 兼容性
- 目前仅支持在[Kotlin](https://kotlinlang.org/)下的订阅事件

## [主要开发者](https://github.com/xbaimiao/mirai-http-sdk/graphs/contributors)
- [xbaimiao (项目创建 主要开发者)](https://github.com/xbaimiao)
- [Kylepoops (开发者)](https://github.com/Kylepoops)
- [wcpe (开发者)](https://github.com/wcpe)
- [killerprojecte (功能开发者)](https://github.com/killerprojecte)

## [Kotlin](https://kotlinlang.org/) 示例
```kotlin
fun main() {
    val wsInfo = WsInfo("http://127.0.0.1:8099/", 123456789, "AccessKey")
    bot = WebSocketBot(wsInfo).connect()
    bot.join()
    bot.eventChancel.subscribe<GroupMessageEvent> {
        val msg = message.contentToString()
        var boolean1 = false
        for (baseComponent in this.message.toList()) {
            if (baseComponent is PlainText) {
                if (baseComponent.string == "禁言") {
                    boolean1 = true
                }
            }
        }
        for (baseComponent in this.message.toList()) {
            if (baseComponent is At) {
                if (boolean1) {
                    group.getMember(baseComponent.target).thenAccept {
                        it!!.mute(600)
                    }
                }
            }
        }
        if (msg == "回复我") {
            group.quoteMessage(Component.text("好"), "${this.messageSource.messageId}")
        }
    }
}
```

## 依赖
```
    maven{
       url = "https://repo.fastmcmirror.org/content/repositories/releases/"
    }
    
    dependencies {
       implementation 'com.xbaimiao:mirai-http-sdk:1.0.4-alpha-b1870ce'
    }
```