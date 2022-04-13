package com.godngu.boilerplate.api.member

import com.godngu.boilerplate.application.member.MemberRegistrationCommand

data class MemberRegistrationRequest(
    val username: String,
    val email: String,
) {
    fun toCommand(): MemberRegistrationCommand {
        return MemberRegistrationCommand(
            username = username,
            email = email,
        )
    }
}
