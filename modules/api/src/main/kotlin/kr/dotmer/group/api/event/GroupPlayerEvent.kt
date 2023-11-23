package kr.dotmer.group.api.event

import kr.dotmer.group.api.group.BaseGroup
import org.bukkit.OfflinePlayer

abstract class GroupPlayerEvent<T : BaseGroup>(
    group: T,
    val player: OfflinePlayer
) : GroupEvent<T>(group)