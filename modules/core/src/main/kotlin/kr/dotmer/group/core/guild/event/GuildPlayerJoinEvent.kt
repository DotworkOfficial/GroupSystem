package kr.dotmer.group.core.guild.event

import kr.dotmer.group.api.event.GroupPlayerJoinEvent
import kr.dotmer.group.core.guild.domain.GuildImpl
import java.util.*

class GuildPlayerJoinEvent(guild: GuildImpl, playerId: UUID) : GroupPlayerJoinEvent<GuildImpl>(guild, playerId)