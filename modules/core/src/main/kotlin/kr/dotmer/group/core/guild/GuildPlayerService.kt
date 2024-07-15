package kr.dotmer.group.core.guild

import kr.dotmer.group.api.group.Guild
import kr.dotmer.group.api.service.GroupService
import kr.dotmer.group.core.base.BaseGroupPlayerService
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.global.core.component.Service

@Service
class GuildPlayerService(
    plugin: HQBukkitPlugin,
    groupService: GroupService<Guild>
) : BaseGroupPlayerService<Guild>(plugin, groupService)