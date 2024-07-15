package kr.dotmer.group.core.town.command

import kr.dotmer.group.api.group.GroupType
import kr.dotmer.group.core.base.BaseGroupAdminCommand
import kr.dotmer.group.core.town.TownService
import kr.dotmer.group.core.town.domain.TownImpl
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(label = "townadmin", isOp = true)
class TownAdminCommand(
    townService: TownService
) : BaseGroupAdminCommand<TownImpl>(townService, GroupType.TOWN) {

    @CommandExecutor(label = "rename", description = "타운 이름을 변경합니다.")
    override suspend fun renameGroup(sender: CommandSender, targetName: String, newName: String) {
        super.renameGroup(sender, targetName, newName)
    }

    @CommandExecutor(label = "changeLeader", description = "타운 리더를 변경합니다.")
    override suspend fun changeLeader(sender: CommandSender, targetName: String, newLeader: Player) {
        super.changeLeader(sender, targetName, newLeader)
    }
}