package kr.dotmer.group.core.town.command

import kr.dotmer.group.api.group.GroupType
import kr.dotmer.group.api.service.GroupService
import kr.dotmer.group.core.base.BaseGroupCommand
import kr.dotmer.group.core.hook.EconomyService
import kr.dotmer.group.core.town.domain.TownImpl
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import kr.hqservice.framework.global.core.component.Qualifier
import org.bukkit.entity.Player

@Command(label = "타운")
class TownCommand(
    @Qualifier("group.town") groupService: GroupService<TownImpl>,
    plugin: HQBukkitPlugin,
    economyService: EconomyService,
) : BaseGroupCommand<TownImpl>(groupService, GroupType.TOWN, economyService, plugin) {
    @CommandExecutor("생성", description = "타운을 생성합니다.")
    override suspend fun createGroup(player: Player, name: String) {
        super.createGroup(player, name)
    }

    @CommandExecutor("초대", description = "타운에 초대합니다.")
    override suspend fun inviteMember(player: Player, target: Player) {
        super.inviteMember(player, target)
    }

    @CommandExecutor("수락", description = "타운 초대를 수락합니다.")
    override suspend fun acceptInvite(player: Player) {
        super.acceptInvite(player)
    }

    @CommandExecutor("거절", description = "타운 초대를 거절합니다.")
    override fun denyInvite(player: Player) {
        super.denyInvite(player)
    }

    @CommandExecutor("탈퇴", description = "타운을 탈퇴합니다.")
    override suspend fun leaveGroup(player: Player) {
        super.leaveGroup(player)
    }

    @CommandExecutor("해체", description = "타운을 해체합니다.")
    override suspend fun disbandGroup(player: Player, name: String) {
        super.disbandGroup(player, name)
    }

    @CommandExecutor("정보", description = "현재 속한 타운의 정보를 확인합니다.")
    override suspend fun groupInfo(player: Player) {
        super.groupInfo(player)
    }
}