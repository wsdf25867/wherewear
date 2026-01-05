package com.wherewear.api.service

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.entity
import org.springframework.ai.content.Media
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils

@Service
class AnalyzeOutfitService(
    private val chatClient: ChatClient,
    @Value("classpath:/prompts/analyze-outfit-template.st")
    private val template: Resource
) {

    data class Input(
        val image: Resource,
        val contentType: String,
        val time: String,
        val place: String,
        val occasion: String
    )

    data class Output(
        val score: Int,
        val summary: String,
        val goodPoint: String,
        val badPoint: String,
        val advice: String
    )

    fun execute(input: Input): Output {
        // 이미지 MIME 타입 결정
        val mimeType = input.contentType.let { MimeTypeUtils.parseMimeType(it) }

        // 이미지를 Media 객체로 변환
        val imageMedia = Media(mimeType, input.image)

        // 회원 프롬프트 구성

        return chatClient.prompt()
            .system("당신은 냉철하지만 센스 있는 패션 전문가 'WhereWear' AI입니다.")
            .user { prompt ->
                prompt
                    .text(template)
                    .params(mapOf(
                        "time" to input.time,
                        "place" to input.place,
                        "occasion" to input.occasion
                    ))
                    .media(imageMedia)
            }
            .call()
            .entity<Output>()
    }
}
