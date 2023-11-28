package kr.dotmer.group.api.service

import kr.dotmer.group.api.group.BaseGroup
import java.util.*

interface GroupService<T : BaseGroup> {
    suspend fun findAll(): List<T>

    suspend fun findByName(name: String): T?

    suspend fun findPlayerGroup(memberUniqueId: UUID): T?

    suspend fun create(name: String, leader: UUID): T

    suspend fun disband(group: T)

    suspend fun addMember(group: T, memberUniqueId: UUID)

    suspend fun removeMember(group: T, memberUniqueId: UUID)

    fun validateGroupName(name: String): Boolean
}