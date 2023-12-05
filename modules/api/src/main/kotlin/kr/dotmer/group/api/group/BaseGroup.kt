package kr.dotmer.group.api.group

import java.util.*

abstract class BaseGroup(
    val id: Long,
    val uniqueId: UUID,
    val name: String,
    val leader: UUID,
    val members: List<UUID>,
    val level: UInt,
) {
    abstract fun isFull(): Boolean

    fun isMember(playerId: UUID): Boolean {
        return members.contains(playerId)
    }

    fun isLeader(playerId: UUID): Boolean {
        return leader == playerId
    }
}