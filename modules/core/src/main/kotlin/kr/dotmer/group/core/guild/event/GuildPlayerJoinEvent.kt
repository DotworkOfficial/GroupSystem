package kr.dotmer.group.core.guild.event

import kr.dotmer.group.api.event.GroupPlayerJoinEvent
import kr.dotmer.group.core.guild.domain.GuildImpl
import org.bukkit.OfflinePlayer

class GuildPlayerJoinEvent(guild: GuildImpl, player: OfflinePlayer) : GroupPlayerJoinEvent<GuildImpl>(guild, player)