package kr.dotmer.group.core.town.event

import kr.dotmer.group.api.event.GroupCreateEvent
import kr.dotmer.group.core.town.domain.TownImpl

class TownCreateEvent(town: TownImpl) : GroupCreateEvent<TownImpl>(town)