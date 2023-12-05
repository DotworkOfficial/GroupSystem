package kr.dotmer.group.api.event.town

import kr.dotmer.group.api.event.base.GroupPlayerJoinEvent
import kr.dotmer.group.api.group.Town
import java.util.*

class TownPlayerJoinEvent(town: Town, playerId: UUID) : GroupPlayerJoinEvent<Town>(town, playerId)