package com.godngu.boilerplate.application.member

interface MemberRegistrationUseCase {

    fun registerMember(command: MemberRegistrationCommand)
}
