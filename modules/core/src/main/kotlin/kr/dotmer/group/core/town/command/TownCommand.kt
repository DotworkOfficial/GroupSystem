package kr.dotmer.group.core.town.command

import kr.dotmer.group.api.group.GroupType
import kr.dotmer.group.api.service.GroupService
import kr.dotmer.group.core.base.BaseGroupCommand
import kr.dotmer.group.core.hook.EconomyService
import kr.dotmer.group.core.hook.ItemProvider
import kr.dotmer.group.core.settings.Settings
import kr.dotmer.group.core.town.domain.TownImpl
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.command.ArgumentLabel
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import kr.hqservice.framework.global.core.component.Qualifier
import org.bukkit.entity.Player

@Command(label = "타운")
class TownCommand(
    @Qualifier("group.town") groupService: GroupService<TownImpl>,
    plugin: HQBukkitPlugin,
    economyService: EconomyService,
    @Qualifier("group.provider.eim") itemProvider: ItemProvider
) : BaseGroupCommand<TownImpl>(
    groupService = groupService,
    groupType = GroupType.TOWN,
    economyService = economyService,
    itemProvider = itemProvider,
    groupSettings = Settings.Town,
    plugin = plugin,
) {
    @CommandExecutor("생성", description = "타운을 생성합니다.")
    override suspend fun createGroup(player: Player, name: String) {
        super.createGroup(player, name)
    }

    @CommandExecutor("초대", description = "타운에 초대합니다.")
    override suspend fun inviteMember(player: Player, target: Player) {
        super.inviteMember(player, target)
    }

    @CommandExecutor("위임", description = "타운 리더를 위임합니다.")
    override suspend fun delegateLeader(player: Player, targetName: String) {
        super.delegateLeader(player, targetName)
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

    @CommandExecutor("추방", description = "멤버를 추방합니다.")
    override suspend fun kickMember(player: Player, @ArgumentLabel("닉네임") targetName: String) {
        super.kickMember(player, targetName)
    }

    @CommandExecutor("진급", description = "타운을 진급시킵니다.")
    override suspend fun levelUpGroup(player: Player) {
        super.levelUpGroup(player)
    }
}