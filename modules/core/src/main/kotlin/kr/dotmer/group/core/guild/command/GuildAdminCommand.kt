package kr.dotmer.group.core.guild.command

import kr.dotmer.group.api.group.GroupType
import kr.dotmer.group.core.base.BaseGroupAdminCommand
import kr.dotmer.group.core.guild.GuildService
import kr.dotmer.group.core.guild.domain.GuildImpl
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(label = "guildadmin", isOp = true)
class GuildAdminCommand(
    guildService: GuildService
) : BaseGroupAdminCommand<GuildImpl>(guildService, GroupType.GUILD) {

    @CommandExecutor(label = "rename", description = "길드 이름을 변경합니다.")
    override suspend fun renameGroup(sender: CommandSender, targetName: String, newName: String) {
        super.renameGroup(sender, targetName, newName)
    }

    @CommandExecutor(label = "changeLeader", description = "길드 리더를 변경합니다.")
    override suspend fun changeLeader(sender: CommandSender, targetName: String, newLeader: Player) {
        super.changeLeader(sender, targetName, newLeader)
    }
}