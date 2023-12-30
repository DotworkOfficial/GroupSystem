package kr.dotmer.group.api.service

import java.util.UUID
import kr.dotmer.group.api.group.BaseGroup

interface GroupService<T : BaseGroup> {
    suspend fun findAll(): List<T>

    suspend fun findByUniqueId(uniqueId: UUID): T?

    suspend fun findByName(name: String): T?

    suspend fun findPlayerGroup(memberUniqueId: UUID): T?

    suspend fun create(name: String, leader: UUID): T

    suspend fun disband(group: T)

    suspend fun addMember(group: T, memberUniqueId: UUID)

    suspend fun removeMember(group: T, memberUniqueId: UUID)

    suspend fun levelUp(group: T)

    fun validateGroupName(name: String): Boolean
}