package com.wherewear.api.controller

import com.wherewear.api.service.AnalyzeOutfitService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["*"]) // HTML 테스트를 위해 CORS 허용
class OutfitController(
    private val analyzeOutfitService: AnalyzeOutfitService
) {

    @PostMapping(
        "/analyze",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        version = "1.0"
    )
    fun analyzeOutfit(
        @RequestPart("image") image: MultipartFile,
        @RequestPart("tpo") request: AnalyzeOutfitRequest
    ): ResponseEntity<AnalyzeOutfitService.Output> {
        val input = AnalyzeOutfitService.Input(
            image = image.resource,
            contentType = image.contentType!!,
            time = request.time,
            place = request.place,
            occasion = request.occasion
        )
        return ResponseEntity.ok(analyzeOutfitService.execute(input))
    }


    data class AnalyzeOutfitRequest(
        val time: String,
        val place: String,
        val occasion: String
    )
}

