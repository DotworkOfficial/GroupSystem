package kr.dotmer.group.core.guild.command

import kr.dotmer.group.api.group.GroupType
import kr.dotmer.group.api.service.GroupService
import kr.dotmer.group.core.base.BaseGroupCommand
import kr.dotmer.group.core.guild.domain.GuildImpl
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import kr.hqservice.framework.global.core.component.Qualifier
import org.bukkit.entity.Player

@Command(label = "길드")
class GuildCommand(
    @Qualifier("group.guild") groupService: GroupService<GuildImpl>,
    plugin: HQBukkitPlugin
) : BaseGroupCommand<GuildImpl>(groupService, GroupType.GUILD, plugin) {
    @CommandExecutor("생성", description = "길드를 생성합니다.")
    override suspend fun createGroup(player: Player, name: String) {
        super.createGroup(player, name)
    }

    @CommandExecutor("초대", description = "길드에 초대합니다.")
    override suspend fun inviteMember(player: Player, target: Player) {
        super.inviteMember(player, target)
    }

    @CommandExecutor("수락", description = "길드 초대를 수락합니다.")
    override suspend fun acceptInvite(player: Player) {
        super.acceptInvite(player)
    }

    @CommandExecutor("거절", description = "길드 초대를 거절합니다.")
    override fun denyInvite(player: Player) {
        super.denyInvite(player)
    }

    @CommandExecutor("해체", description = "길드를 해체합니다.")
    override suspend fun disbandGroup(player: Player, name: String) {
        super.disbandGroup(player, name)
    }

    @CommandExecutor("정보", description = "현재 속한 길드의 정보를 확인합니다.")
    override suspend fun groupInfo(player: Player) {
        super.groupInfo(player)
    }
}