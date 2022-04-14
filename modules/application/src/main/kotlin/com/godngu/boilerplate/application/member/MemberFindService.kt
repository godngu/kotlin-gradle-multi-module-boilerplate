package com.godngu.boilerplate.application.member

import com.godngu.boilerplate.application.common.page.PageModel
import com.godngu.boilerplate.application.common.page.PageModelMapper
import com.godngu.boilerplate.application.common.page.PageRequestModel
import com.godngu.boilerplate.domain.common.UseCase
import com.godngu.boilerplate.domain.member.Member
import com.godngu.boilerplate.domain.member.MemberRepository
import org.springframework.data.domain.Page

@UseCase
class MemberFindService(
    val memberRepository: MemberRepository
) : MemberFindUseCase {

    override fun findMembers(query: PageRequestModel): PageModel<MemberDto> {
        saveMembersForTest()
        query.sort
        val page: Page<Member> = memberRepository.findAll(query.transform())

        return PageModelMapper.map(
            page.map(MemberDto.Companion::of)
        )
    }

    private fun saveMembersForTest() {
        val elements: Long = 22
        for (i in (1..elements)) {
            memberRepository.save(Member("name$i", "email$i@gmail.com"))
        }
    }
}
