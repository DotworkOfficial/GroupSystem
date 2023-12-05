package kr.dotmer.group.api.event.town

import kr.dotmer.group.api.event.base.GroupCreateEvent
import kr.dotmer.group.api.group.Town

class TownCreateEvent(town: Town) : GroupCreateEvent<Town>(town)