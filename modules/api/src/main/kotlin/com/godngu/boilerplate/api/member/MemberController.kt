package com.godngu.boilerplate.api.member

import com.godngu.boilerplate.application.common.page.PageModel
import com.godngu.boilerplate.application.common.page.PageRequestModel
import com.godngu.boilerplate.application.common.page.SortModel
import com.godngu.boilerplate.application.member.MemberDto
import com.godngu.boilerplate.application.member.MemberFindUseCase
import com.godngu.boilerplate.application.member.MemberRegistrationUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    val memberRegistrationUseCase: MemberRegistrationUseCase,
    val memberFindUseCase: MemberFindUseCase,
) {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello bro~"
    }

    @PostMapping("/api/v1/member")
    fun registerMember(@RequestBody request: MemberRegistrationRequest) {
        val command = request.toCommand()
        memberRegistrationUseCase.registerMember(command)
    }

    @GetMapping("/api/v1/members")
    fun findMembers(): PageModel<MemberDto> {
        val pageRequestModel = PageRequestModel.of(2, 3, SortModel.Direction.DESC, "id")

        return memberFindUseCase.findMembers(pageRequestModel)
    }
}
