package com.godngu.boilerplate.application.member

import com.godngu.boilerplate.domain.common.UseCase
import com.godngu.boilerplate.domain.member.MemberRepository
import com.godngu.boilerplate.domain.member.MemberService
import org.springframework.transaction.annotation.Transactional

@UseCase
class MemberRegistrationService(
    val memberService: MemberService,
    val memberRepository: MemberRepository,
) : MemberRegistrationUseCase {

    @Transactional
    override fun registerMember(command: MemberRegistrationCommand) {
        val member = command.toDomain()
        if (memberService.isExists(member)) {
            // TODO: 커스텀 익셉션으로 변경하기
            throw RuntimeException("이미 사용중인 이메일 입니다.")
        }
        val savedMember = memberRepository.save(member)
    }
}
