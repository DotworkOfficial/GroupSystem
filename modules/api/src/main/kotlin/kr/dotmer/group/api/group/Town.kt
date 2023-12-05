package kr.dotmer.group.api.group

import java.util.*

abstract class Town(
    id: Long,
    uniqueId: UUID,
    name: String,
    leader: UUID,
    members: List<UUID>,
    level: UInt,
) : BaseGroup(id, uniqueId, name, leader, members, level)