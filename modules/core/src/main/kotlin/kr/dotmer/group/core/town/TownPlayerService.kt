package kr.dotmer.group.core.town

import kr.dotmer.group.api.group.Town
import kr.dotmer.group.api.service.GroupService
import kr.dotmer.group.core.base.BaseGroupPlayerService
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.global.core.component.Service

@Service
class TownPlayerService(
    plugin: HQBukkitPlugin,
    groupService: GroupService<Town>
) : BaseGroupPlayerService<Town>(plugin, groupService)