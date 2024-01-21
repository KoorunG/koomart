package com.kotlin.koomart.domain.post

import com.kotlin.koomart.domain.common.PrimaryKeyEntity
import com.kotlin.koomart.domain.member.Member
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.util.*

@Entity
class Post(
    title: String,
    contents: String,
    author: Member,
): PrimaryKeyEntity() {

    @Column(nullable = false)
    var title = title
        protected set

    @Column(nullable = true)
    var contents = contents
        protected set

    @ManyToOne(targetEntity = Member::class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var author = author
        protected set
}