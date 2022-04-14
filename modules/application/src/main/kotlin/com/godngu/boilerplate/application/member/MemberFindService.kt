package com.godngu.boilerplate.application.member

import com.godngu.boilerplate.application.common.page.PageModel
import com.godngu.boilerplate.application.common.page.PageModelMapper
import com.godngu.boilerplate.application.common.page.PageRequestModel
import com.godngu.boilerplate.domain.common.UseCase
import com.godngu.boilerplate.domain.member.Member
import com.godngu.boilerplate.domain.member.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@UseCase
class MemberFindService(
    val memberRepository: MemberRepository
) : MemberFindUseCase {

    @PostConstruct
    fun setUp() {
        saveMembersForTest()
    }

    @Transactional(readOnly = true)
    override fun findMembers(query: PageRequestModel): PageModel<MemberDto> {
        val page: Page<Member> = memberRepository.findAll(query.convert())
        return PageModelMapper.map(page, MemberDto.Converter::of)
    }

    private fun saveMembersForTest() {
        val elements: Long = 22
        for (i in (1..elements)) {
            memberRepository.save(Member("name$i", "email$i@gmail.com"))
        }
    }
}
