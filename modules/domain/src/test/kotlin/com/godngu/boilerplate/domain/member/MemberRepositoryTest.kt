package com.godngu.boilerplate.domain.member

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.annotation.Rollback

@SpringBootTest
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun saveMembers() {
        for (i in (1..100)) {
            memberRepository.save(Member("name$i", "email$i@gmail.com"))
        }
        val members = memberRepository.findAll()
        println("members = $members")
    }

    @Test
    fun paging() {
        // given
        val elements: Long = 22
        for (i in (1..elements)) {
            memberRepository.save(Member("name$i", "email$i@gmail.com"))
        }

        val currentPage: Int = 0
        val size: Int = 10
        val pageRequest = PageRequest.of(currentPage, size, Sort.by(Sort.Direction.DESC, "id"))

        // when
        val page: Page<Member> = memberRepository.findAll(pageRequest)

        // then
        val content = page.content
        for (member in content) {
            println("member = $member")
        }
        assertThat(content.size).isEqualTo(size)
        assertThat(page.totalPages).isEqualTo(3)
        assertThat(page.totalElements).isEqualTo(elements)
        assertThat(page.number).isEqualTo(currentPage)
        assertThat(page.isFirst).isTrue
        assertThat(page.hasNext()).isTrue
    }
}
