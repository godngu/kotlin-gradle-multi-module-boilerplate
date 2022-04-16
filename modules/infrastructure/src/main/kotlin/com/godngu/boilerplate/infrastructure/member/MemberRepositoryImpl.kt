package com.godngu.boilerplate.infrastructure.member

import com.godngu.boilerplate.domain.member.Member
import com.godngu.boilerplate.domain.member.MemberCustomRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.persistence.PersistenceContext

@Repository
class MemberRepositoryImpl : MemberCustomRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    @Transactional(readOnly = true)
    override fun findByEmailString(email: String): Optional<Member> {
        return try {
            Optional.of(
                em.createQuery("select m from Member m where m.email = :email", Member::class.java)
                    .setParameter("email", email)
                    .singleResult
            )
        } catch (e: NoResultException) {
            Optional.empty()
        }
    }

    @Transactional(readOnly = true)
    override fun findByEmail(member: Member): Member {
        return em.createQuery("select m from Member m where m.email = :email", Member::class.java)
            .setParameter("email", member.email)
            .singleResult
    }
}
