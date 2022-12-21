package com.xbaimiao.mirai.tools

import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.net.ConnectException
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*
import javax.net.ssl.HttpsURLConnection

private object MicrosoftTTS {
    fun genAudio(text: String): String {
        val url = URL("https://southeastasia.tts.speech.microsoft.com/cognitiveservices/v1")
        val connection = url.openConnection() as HttpsURLConnection
        connection.requestMethod = "POST"
        connection.doInput = true
        connection.doOutput = true
        connection.setRequestProperty("accept", "*/*")
        connection.setRequestProperty(
            "authorization",
            "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6ImtleTEiLCJ0eXAiOiJKV1QifQ.eyJyZWdpb24iOiJzb3V0aGVhc3Rhc2lhIiwic3Vic2NyaXB0aW9uLWlkIjoiYzk2NTQ2ODdhNzdkNDA2NjliYzRkNzhhNGIyNmYyMzgiLCJwcm9kdWN0LWlkIjoiQ29nbml0aXZlU2VydmljZXMuUzAiLCJjb2duaXRpdmUtc2VydmljZXMtZW5kcG9pbnQiOiJodHRwczovL2FwaS5jb2duaXRpdmUubWljcm9zb2Z0LmNvbS9pbnRlcm5hbC92MS4wLyIsImF6dXJlLXJlc291cmNlLWlkIjoiL3N1YnNjcmlwdGlvbnMvMTUzYTFlMTEtYTU5Yi00M2IxLTk5ZWUtNGI2NmVlMjk1ZDljL3Jlc291cmNlR3JvdXBzL1Byb2RFc3NlbnRpYWxzL3Byb3ZpZGVycy9NaWNyb3NvZnQuQ29nbml0aXZlU2VydmljZXMvYWNjb3VudHMvU1RDSVRyYW5zbGF0aW9uQW5zd2VyTmV1cmFsQXNpYSIsInNjb3BlIjpbInNwZWVjaHRvaW50ZW50cyIsImh0dHBzOi8vYXBpLm1pY3Jvc29mdHRyYW5zbGF0b3IuY29tLyIsInNwZWVjaHNlcnZpY2VzIiwidmlzaW9uIl0sImF1ZCI6WyJ1cm46bXMuc3BlZWNoIiwidXJuOm1zLmx1aXMuc291dGhlYXN0YXNpYSIsInVybjptcy5taWNyb3NvZnR0cmFuc2xhdG9yIiwidXJuOm1zLnNwZWVjaHNlcnZpY2VzLnNvdXRoZWFzdGFzaWEiLCJ1cm46bXMudmlzaW9uLnNvdXRoZWFzdGFzaWEiXSwiZXhwIjoxNjY5OTY0OTA4LCJpc3MiOiJ1cm46bXMuY29nbml0aXZlc2VydmljZXMifQ.9FXiMqhdPM0KfRhieRNa6iyE9LL9krd6lrnr_848Uq3kdTa_kifGOPA_HU5-v_PLp7tYmsD9VdbF2nz7MqHeuA"
        )
        connection.setRequestProperty("content-type", "application/ssml+xml")
        connection.setRequestProperty(
            "user-agent",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
        )
        connection.setRequestProperty("x-microsoft-outputformat", "amr-wb-16000hz")
        val outputStreamWriter = OutputStreamWriter(connection.outputStream, StandardCharsets.UTF_8)
        outputStreamWriter.append(
            """
            <speak version='1.0' xml:lang='zh-CN'><voice xml:lang='zh-CN' xml:gender='Female' name='zh-CN-XiaoxiaoNeural'><prosody rate='-20.00%'>${text}</prosody></voice></speak>
        """.trimIndent()
        )
        outputStreamWriter.flush()
        outputStreamWriter.close()
        if (connection.responseCode != 200) {
            throw ConnectException("Error code: " + connection.responseCode)
        }
        val file = File("audio.amr")
        val inputStream = connection.inputStream
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes)
        inputStream.close()
        val fileWriter = FileOutputStream(file)
        fileWriter.write(bytes)
        fileWriter.flush()
        fileWriter.close()
        return Base64.getEncoder().encodeToString(bytes)
    }
}