package com.godngu.boilerplate.domain.member

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class Member(
    @Column(name = "username") val username: String,
    @Column(name = "email") val email: String,
) {
    @Id
    @GeneratedValue
    @Column(name = "member_no")
    val id: Long = 0L

    override fun toString(): String {
        return "Member(username='$username', email='$email', id=$id)"
    }
}