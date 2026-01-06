package com.wherewear.api.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun `Member 저장 및 조회 테스트`() {
        val member = Member(
            provider = "kakao",
            providerId = "12345",
            credits = 0
        )
        val savedMember = memberRepository.save(member)

        val foundMember = memberRepository.findById(savedMember.id!!).orElseThrow()

        assertEquals(member.provider, foundMember.provider)
        assertEquals(member.providerId, foundMember.providerId)
        assertEquals(member.credits, foundMember.credits)
    }
}
