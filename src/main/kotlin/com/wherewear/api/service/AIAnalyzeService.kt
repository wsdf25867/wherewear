package com.wherewear.api.service

import com.wherewear.api.dto.AnalysisResponse
import com.wherewear.api.dto.TpoRequest
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.content.Media
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import org.springframework.web.multipart.MultipartFile
import tools.jackson.databind.ObjectMapper

@Service
class AIAnalyzeService(
    private val chatClient: ChatClient,
): AnalyzeService {

    override fun analyzeOutfit(image: MultipartFile, tpo: TpoRequest): AnalysisResponse {
        // 이미지 MIME 타입 결정
        val mimeType = image.contentType?.let { MimeTypeUtils.parseMimeType(it) }
            ?: MimeTypeUtils.IMAGE_JPEG

        // 이미지를 Media 객체로 변환
        val imageMedia = Media(mimeType, image.resource)

        // 사용자 프롬프트 구성
        val userPrompt = """
            사용자의 TPO 정보를 바탕으로 옷차림을 평가해주세요.
            
            [TPO 정보]
            - 시간/계절: ${tpo.time}
            - 장소: ${tpo.place}
            - 상황: ${tpo.occasion}
            
            이미지에 있는 옷차림을 분석하고, score(0~100), summary(한 줄 총평), 
            goodPoint(좋은 점), badPoint(아쉬운 점), advice(구체적인 개선 조언)를 제공해주세요.
        """.trimIndent()

        return chatClient.prompt()
            .system("당신은 냉철하지만 센스 있는 패션 전문가 'WhereWear' AI입니다.")
            .user { userSpec ->
                userSpec
                    .text(userPrompt)
                    .media(imageMedia)
            }
            .call()
            .entity(AnalysisResponse::class.java) ?: throw IllegalStateException()
    }
}
