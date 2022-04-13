package com.godngu.boilerplate.application.member

import com.godngu.boilerplate.domain.member.Member

data class MemberRegistrationCommand(
    val username: String,
    // TODO : 이메일 규칙 검사로 require 사용 해보기
    val email: String,
) {
    fun toDomain(): Member {
        return Member(
            username = username,
            email = email,
        )
    }
}
