package kr.dotmer.group.core.town.event

import kr.dotmer.group.api.event.GroupPlayerLeaveEvent
import kr.dotmer.group.core.town.domain.TownImpl
import java.util.*

class TownPlayerLeaveEvent(town: TownImpl, playerId: UUID) : GroupPlayerLeaveEvent<TownImpl>(town, playerId)