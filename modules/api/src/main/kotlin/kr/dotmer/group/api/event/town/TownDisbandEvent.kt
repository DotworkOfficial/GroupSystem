package kr.dotmer.group.api.event.town

import kr.dotmer.group.api.event.base.GroupDisbandEvent
import kr.dotmer.group.api.group.Town

class TownDisbandEvent(town: Town) : GroupDisbandEvent<Town>(town)