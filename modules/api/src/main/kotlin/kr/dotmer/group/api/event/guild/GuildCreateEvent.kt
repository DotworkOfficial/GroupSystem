package kr.dotmer.group.api.event.guild

import kr.dotmer.group.api.event.base.GroupCreateEvent
import kr.dotmer.group.api.group.Guild

class GuildCreateEvent(guild: Guild) : GroupCreateEvent<Guild>(guild)