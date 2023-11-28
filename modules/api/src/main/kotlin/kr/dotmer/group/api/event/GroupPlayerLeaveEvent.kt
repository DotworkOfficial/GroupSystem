package kr.dotmer.group.api.event

import kr.dotmer.group.api.group.BaseGroup
import java.util.*

abstract class GroupPlayerLeaveEvent<T : BaseGroup>(group: T, playerId: UUID) :
    GroupPlayerEvent<T>(group, playerId)