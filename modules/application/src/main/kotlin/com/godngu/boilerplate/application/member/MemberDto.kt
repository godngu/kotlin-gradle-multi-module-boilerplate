package com.godngu.boilerplate.application.member

import com.godngu.boilerplate.domain.member.Member

data class MemberDto(
    val memberNo: Long,
    val username: String,
    val email: String,
) {
    companion object {
        fun of(member: Member): MemberDto {
            return MemberDto(
                memberNo = member.id,
                username = member.username,
                email = member.email,
            )
        }
    }
}
