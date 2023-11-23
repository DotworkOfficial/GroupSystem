package kr.dotmer.group.core.base

import kr.dotmer.group.api.group.BaseGroup
import kr.dotmer.group.api.service.GroupService
import kr.hqservice.framework.bukkit.core.extension.sendColorizedMessage
import org.bukkit.entity.Player

abstract class BaseGroupCommand<T : BaseGroup>(
    private val groupService: GroupService<T>

) {
    suspend fun createGroup(player: Player, name: String) {
        val group = groupService.create(name, player.uniqueId)
        groupService.addMember(group, player.uniqueId)
    }

    suspend fun inviteMember(player: Player, target: Player) {
        TODO()
    }

    suspend fun disbandGroup(player: Player) {
        val playerGroup = groupService.findPlayerGroup(player.uniqueId)

        if (playerGroup == null) {
            player.sendColorizedMessage("&c어떤 곳에도 속해있지 않습니다.")
            return
        }

        if (!playerGroup.isLeader(player.uniqueId)) {
            player.sendColorizedMessage("&c리더만 해체할 수 있습니다.")
            return
        }

        groupService.disband(playerGroup)
        player.sendColorizedMessage("&a성공적으로 해체하였습니다.")
    }
}