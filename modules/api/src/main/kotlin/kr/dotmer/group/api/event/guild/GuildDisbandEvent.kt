package kr.dotmer.group.api.event.guild

import kr.dotmer.group.api.event.base.GroupDisbandEvent
import kr.dotmer.group.api.group.Guild

class GuildDisbandEvent(guild: Guild) : GroupDisbandEvent<Guild>(guild)