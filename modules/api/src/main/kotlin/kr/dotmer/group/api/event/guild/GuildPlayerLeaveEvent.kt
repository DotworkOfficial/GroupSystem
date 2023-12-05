package kr.dotmer.group.api.event.guild

import kr.dotmer.group.api.event.base.GroupPlayerLeaveEvent
import kr.dotmer.group.api.group.Guild
import java.util.*

class GuildPlayerLeaveEvent(guild: Guild, playerId: UUID) : GroupPlayerLeaveEvent<Guild>(guild, playerId)