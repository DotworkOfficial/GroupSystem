package kr.dotmer.group.core.base

import kr.dotmer.group.api.group.BaseGroup
import kr.dotmer.group.api.group.GroupType
import kr.dotmer.group.api.service.GroupService
import kr.hqservice.framework.bukkit.core.extension.sendColorizedMessage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class BaseGroupAdminCommand<T : BaseGroup>(
    private val groupService: GroupService<T>,
    private val groupType: GroupType,
) {
    open suspend fun renameGroup(sender: CommandSender, targetName: String, newName: String) {
        val group = groupService.findByName(targetName)

        if (group == null) {
            sender.sendColorizedMessage("&c존재하지 않는 ${groupType.koreanName}입니다.")
            return
        }

        if (groupService.findByName(newName) != null) {
            sender.sendColorizedMessage("&c이미 존재하는 ${groupType.koreanName}명입니다.")
            return
        }

        groupService.setGroupName(group, newName)
        sender.sendColorizedMessage("&a성공적으로 이름을 변경하였습니다.")
    }

    open suspend fun changeLeader(sender: CommandSender, targetName: String, newLeader: Player) {
        val group = groupService.findByName(targetName)

        if (group == null) {
            sender.sendColorizedMessage("&c존재하지 않는 ${groupType.koreanName}입니다.")
            return
        }

        groupService.delegateLeader(group, newLeader.uniqueId)
        sender.sendColorizedMessage("&a성공적으로 리더를 변경하였습니다.")
    }
}