package kr.dotmer.group.api.group

import java.util.*

abstract class Town(
    id: Long,
    name: String,
    leader: UUID,
    members: List<UUID>,
    level: UInt,
) : BaseGroup(id, name, leader, members, level)