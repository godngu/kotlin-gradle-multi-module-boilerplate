package com.godngu.boilerplate.api.member

import com.godngu.boilerplate.application.member.MemberRegistrationUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    val memberRegistrationUseCase: MemberRegistrationUseCase
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
}
