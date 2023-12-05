package kr.dotmer.group.api.event.guild

import kr.dotmer.group.api.event.base.GroupPlayerJoinEvent
import kr.dotmer.group.api.group.Guild
import java.util.*

class GuildPlayerJoinEvent(guild: Guild, playerId: UUID) : GroupPlayerJoinEvent<Guild>(guild, playerId)