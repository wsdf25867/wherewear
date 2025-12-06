package com.wherewear.api.controller

import com.wherewear.api.dto.AnalysisResponse
import com.wherewear.api.dto.TpoRequest
import com.wherewear.api.service.AnalyzeService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = ["*"]) // HTML 테스트를 위해 CORS 허용
class AnalysisController(
    private val analyzeService: AnalyzeService
) {

    @PostMapping(
        "/analyze",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun analyzeOutfit(
        @RequestPart("image") image: MultipartFile,
        @RequestPart("tpo") tpo: TpoRequest
    ): AnalysisResponse {
        return analyzeService.analyzeOutfit(image, tpo)
    }
}
