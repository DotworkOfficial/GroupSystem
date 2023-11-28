package kr.dotmer.group.core.town.domain

import kr.dotmer.group.api.group.Town
import java.util.*

class TownImpl(
    id: Long,
    name: String,
    leader: UUID,
    members: List<UUID>,
    level: UInt,
) : Town(id, name, leader, members, level) {
    override fun isFull(): Boolean {
        return members.size.toUInt() >= level * 3u
    }
}