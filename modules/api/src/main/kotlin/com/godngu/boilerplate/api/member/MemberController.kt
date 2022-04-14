package com.godngu.boilerplate.api.member

import com.godngu.boilerplate.application.common.page.PageModel
import com.godngu.boilerplate.application.common.page.PageModelMapper
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

    /**
     * application 응답 결과를 API 응답으로 사용할 때
     * @return PageModel<MemberDto>
     */
    @GetMapping("/api/v1/members1")
    fun findMembers1(): PageModel<MemberDto> {
        val pageRequestModel = PageRequestModel.of(2, 3, SortModel.Direction.DESC, "id")
        return memberFindUseCase.findMembers(pageRequestModel)
    }

    /**
     * application 응답 결과를 api 전용 DTO로 변환하여 API 응답으로 사용할 때
     * @return PageModel<MemberResponse>
     */
    @GetMapping("/api/v1/members2")
    fun findMembers2(): PageModel<MemberResponse> {
        val pageRequestModel = PageRequestModel.of(2, 3, SortModel.Direction.DESC, "id")
        val page: PageModel<MemberDto> = memberFindUseCase.findMembers(pageRequestModel)
        return PageModelMapper.map(page, MemberResponse.Converter::of)
    }
}
