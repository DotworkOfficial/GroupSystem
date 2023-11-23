package kr.dotmer.group.core.town.event

import kr.dotmer.group.api.event.GroupPlayerJoinEvent
import kr.dotmer.group.core.town.domain.TownImpl
import org.bukkit.OfflinePlayer

class TownPlayerJoinEvent(town: TownImpl, player: OfflinePlayer) : GroupPlayerJoinEvent<TownImpl>(town, player)