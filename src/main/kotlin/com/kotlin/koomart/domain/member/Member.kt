package com.kotlin.koomart.domain.member

import com.kotlin.koomart.domain.common.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Member(
    loginId: String,
    name: String,
    password: String,
) : PrimaryKeyEntity() {

    @Column(nullable = false)
    var loginId = loginId
        protected set

    @Column(nullable = false)
    var name = name
        protected set

    @Column(nullable = false)
    var password = password
        protected set
}