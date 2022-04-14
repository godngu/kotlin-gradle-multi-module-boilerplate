package com.godngu.boilerplate.api.member

import com.godngu.boilerplate.application.member.MemberDto

class MemberResponse(
    val memberNo: Long,
    val userName: String,
    val emailAddress: String,
) {
    companion object Converter {
        fun of(member: MemberDto): MemberResponse {
            return MemberResponse(
                memberNo = member.memberNo,
                userName = member.username,
                emailAddress = member.email,
            )
        }
    }
}
