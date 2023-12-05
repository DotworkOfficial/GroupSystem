package kr.dotmer.group.core.guild.domain

import kr.dotmer.group.api.group.Guild
import java.util.*

class GuildImpl(
    id: Long,
    uniqueId: UUID,
    name: String,
    leader: UUID,
    members: List<UUID>,
    level: UInt,
) : Guild(id, uniqueId, name, leader, members, level) {
    override fun isFull(): Boolean {
        return members.size.toUInt() >= level * 3u
    }
}
