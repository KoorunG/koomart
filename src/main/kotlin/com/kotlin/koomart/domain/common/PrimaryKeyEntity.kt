package com.kotlin.koomart.domain.common

import com.github.f4b6a3.ulid.UlidCreator
import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.util.*
import kotlin.jvm.Transient

@MappedSuperclass
abstract class PrimaryKeyEntity(
    _id: UUID?
) : Persistable<UUID> {

    // 생성시간순으로 오름차순 정렬되므로 인덱싱에 유리함!
    @Id
    @Column(columnDefinition = "uuid")
    private val id: UUID = _id ?: UlidCreator.getMonotonicUlid().toUuid()

    // 내부적으로 사용되는 신규생성여부 체크 프로퍼티
    @Transient
    private var _isNew = true
    override fun getId(): UUID = id
    override fun isNew(): Boolean = _isNew

    @PostPersist // 영속화 이후
    @PostLoad    // 영속화된 데이터 조회 이후
    protected fun load() {
        _isNew = false
    }

    // 동일한 식별자를 가질 경우 동일 엔티티로 판단해야 하므로 equals, hashCode 재정의 필요
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is HibernateProxy && this::class != other::class) return false
        return id == identifier(other)
    }

    // 대상객체가 프록시객체일 경우 처리 함수
    private fun identifier(obj: Any) = when(obj) {
        is HibernateProxy -> obj.hibernateLazyInitializer.identifier
        else -> {
            obj as PrimaryKeyEntity
            obj.id
        }
    }

    override fun hashCode() = Objects.hashCode(id)
}