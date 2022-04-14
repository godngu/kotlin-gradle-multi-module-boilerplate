package com.godngu.boilerplate.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>, MemberCustomRepository {

    fun existsByEmail(email: String): Boolean
}
