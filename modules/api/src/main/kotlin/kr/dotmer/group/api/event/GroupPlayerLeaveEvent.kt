package kr.dotmer.group.api.event

import kr.dotmer.group.api.group.BaseGroup
import org.bukkit.OfflinePlayer

abstract class GroupPlayerLeaveEvent<T : BaseGroup>(group: T, player: OfflinePlayer) :
    GroupPlayerEvent<T>(group, player)