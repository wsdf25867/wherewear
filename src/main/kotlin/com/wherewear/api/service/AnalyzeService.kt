package com.wherewear.api.service

import com.wherewear.api.dto.AnalysisResponse
import com.wherewear.api.dto.TpoRequest
import org.springframework.web.multipart.MultipartFile

interface AnalyzeService {
    fun analyzeOutfit(image: MultipartFile, tpo: TpoRequest): AnalysisResponse
}
