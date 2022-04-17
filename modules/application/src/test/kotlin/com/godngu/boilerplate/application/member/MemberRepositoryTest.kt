package com.godngu.boilerplate.application.member

import com.godngu.boilerplate.domain.member.Member
import com.godngu.boilerplate.domain.member.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun findByEmail() {
        // given
        val email = "kil@gmail.com"
        val member = Member("kil", email)
        val savedMember = memberRepository.save(member)

        // when
        val foundMember = memberRepository.findByEmail(savedMember)

        // then
        assertThat(foundMember.email).isEqualTo(email)
    }
}
