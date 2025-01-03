package kr.dotmer.group.api.event.base

import kr.dotmer.group.api.group.BaseGroup
import java.util.*

abstract class GroupPlayerJoinEvent<T : BaseGroup>(group: T, playerId: UUID) : GroupPlayerEvent<T>(group, playerId)