package kr.dotmer.group.api.repository

import java.util.*

interface GroupRepository<T> {
    suspend fun findById(id: Long): T?

    suspend fun findByUniqueId(uniqueId: UUID): T?

    suspend fun findByName(name: String): T?

    suspend fun findPlayerGroup(memberUniqueId: UUID): T?

    suspend fun findAll(): List<T>

    suspend fun new(entity: T.() -> Unit): T

    suspend fun update(id: Long, entity: T.() -> Unit)

    suspend fun delete(id: Long)

    suspend fun addMember(id: Long, memberUniqueId: UUID)

    suspend fun removeMember(id: Long, memberUniqueId: UUID)

    suspend fun getMembers(id: Long): List<UUID>
}