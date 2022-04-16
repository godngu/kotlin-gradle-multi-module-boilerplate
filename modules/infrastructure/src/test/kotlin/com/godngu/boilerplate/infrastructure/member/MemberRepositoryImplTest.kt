package com.godngu.boilerplate.infrastructure.member

import com.godngu.boilerplate.domain.member.Member
import com.godngu.boilerplate.domain.member.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("domain-local", "infrastructure-local")
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun findByEmail_from_custom_repository() {
        // given
        val email = "member1@test.com"
        val member = Member("member1", email)
        val savedMember = memberRepository.save(member)

        // when
        val foundMember = memberRepository.findByEmail(savedMember)

        // then
        assertThat(foundMember.email).isEqualTo(email)
    }

    @Test
    @DisplayName("응답 결과가 없을 경우 Optional.empty()로 받는다.")
    fun name() {
        // given
        val notExistsEmail = "ddddddddddddddddddddddddd"

        // when
        val foundMember = memberRepository.findByEmailString(notExistsEmail)

        // then
        assertThat(foundMember).isEmpty
    }
}