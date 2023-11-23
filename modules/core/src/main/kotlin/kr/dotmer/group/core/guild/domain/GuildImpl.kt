package kr.dotmer.group.core.guild.domain

import kr.dotmer.group.api.group.Guild
import java.util.*

class GuildImpl(
    id: Long,
    name: String,
    leader: UUID,
    members: List<UUID>,
    level: UInt,
) : Guild(id, name, leader, members, level) {
    override fun isFull(): Boolean {
        TODO()
    }
}
