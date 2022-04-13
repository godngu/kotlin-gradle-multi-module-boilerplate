package com.godngu.boilerplate.domain.member

import org.springframework.stereotype.Service

@Service
class MemberService(
    val memberRepository: MemberRepository
) {
    fun isExists(member: Member): Boolean {
        return memberRepository.existsByEmail(member.email)
    }
}
