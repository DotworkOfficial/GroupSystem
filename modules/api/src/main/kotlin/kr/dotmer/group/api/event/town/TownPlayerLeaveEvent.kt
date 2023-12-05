package kr.dotmer.group.api.event.town

import kr.dotmer.group.api.event.base.GroupPlayerLeaveEvent
import kr.dotmer.group.api.group.Town
import java.util.*

class TownPlayerLeaveEvent(town: Town, playerId: UUID) : GroupPlayerLeaveEvent<Town>(town, playerId)