```kotlin
fun main() {
    val wsInfo = WsInfo("http://127.0.0.1:8099/", 2157207381, "INITKEYCgTu5Bcx")
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