package kr.dotmer.group.api.event

import kr.dotmer.group.api.group.BaseGroup
import java.util.*

abstract class GroupPlayerEvent<T : BaseGroup>(
    group: T,
    val playerId: UUID
) : GroupEvent<T>(group)