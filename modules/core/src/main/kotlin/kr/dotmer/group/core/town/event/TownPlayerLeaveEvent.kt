package kr.dotmer.group.core.town.event

import kr.dotmer.group.api.event.GroupPlayerLeaveEvent
import kr.dotmer.group.core.town.domain.TownImpl
import org.bukkit.OfflinePlayer

class TownPlayerLeaveEvent(town: TownImpl, player: OfflinePlayer) : GroupPlayerLeaveEvent<TownImpl>(town, player)