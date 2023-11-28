package kr.dotmer.group.core.town.event

import kr.dotmer.group.api.event.GroupPlayerJoinEvent
import kr.dotmer.group.core.town.domain.TownImpl
import java.util.*

class TownPlayerJoinEvent(town: TownImpl, playerId: UUID) : GroupPlayerJoinEvent<TownImpl>(town, playerId)