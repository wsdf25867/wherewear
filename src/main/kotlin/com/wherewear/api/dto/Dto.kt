package com.wherewear.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

// 클라이언트(HTML/iOS)에서 보낼 TPO 정보
data class TpoRequest(
    val time: String,      // 예: "여름 낮 2시"
    val place: String,     // 예: "결혼식장"
    val occasion: String   // 예: "하객"
)

// AI가 분석해서 내려줄 결과 (JSON 구조)
data class AnalysisResponse(
    val score: Int,
    val summary: String,
    val goodPoint: String,
    val badPoint: String,
    val advice: String
)
