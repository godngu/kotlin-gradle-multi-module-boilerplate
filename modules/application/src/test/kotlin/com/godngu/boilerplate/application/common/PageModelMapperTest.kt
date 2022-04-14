package com.godngu.boilerplate.application.common

import com.godngu.boilerplate.application.member.MemberFindUseCase
import com.godngu.boilerplate.domain.member.Member
import com.godngu.boilerplate.domain.member.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PageModelMapperTest {

    @Autowired
    lateinit var memberFindUseCase: MemberFindUseCase

    @Test
    @DisplayName("엔티티 객체 변환시 익셉션 발생")
    fun name() {

    }
}