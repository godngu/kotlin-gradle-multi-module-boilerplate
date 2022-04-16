package com.godngu.boilerplate.domain.member

import java.util.Optional

interface MemberCustomRepository {

    fun findByEmailString(email: String): Optional<Member>

    fun findByEmail(member: Member): Member
}
