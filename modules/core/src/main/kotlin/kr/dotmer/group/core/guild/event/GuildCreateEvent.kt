package kr.dotmer.group.core.guild.event

import kr.dotmer.group.api.event.GroupCreateEvent
import kr.dotmer.group.core.guild.domain.GuildImpl

class GuildCreateEvent(guild: GuildImpl) : GroupCreateEvent<GuildImpl>(guild)