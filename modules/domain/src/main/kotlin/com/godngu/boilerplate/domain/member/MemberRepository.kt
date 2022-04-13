package com.godngu.boilerplate.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun existsByEmail(email: String): Boolean
}
