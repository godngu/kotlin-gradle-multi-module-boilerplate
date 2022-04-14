package com.godngu.boilerplate.application.member

import com.godngu.boilerplate.application.common.page.PageRequestModel
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberFindUseCaseTest {

    @Autowired
    lateinit var memberFindUseCase: MemberFindUseCase

    @Test
    fun name() {
        val command = PageRequestModel()
        memberFindUseCase.findMembers(command)
    }
}
