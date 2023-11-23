package kr.dotmer.group.core.town.command

import kr.dotmer.group.api.service.GroupService
import kr.dotmer.group.core.base.BaseGroupCommand
import kr.dotmer.group.core.town.domain.TownImpl
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import kr.hqservice.framework.global.core.component.Qualifier
import org.bukkit.entity.Player

@Command(label = "마을")
class TownCommand(
    @Qualifier("group.town") groupService: GroupService<TownImpl>
) : BaseGroupCommand<TownImpl>(groupService) {
    @CommandExecutor("생성", description = "마을을 생성합니다.")
    suspend fun createTown(player: Player, name: String) {
        createGroup(player, name)
    }

    @CommandExecutor("해체", description = "마을을 해체합니다.")
    suspend fun disbandTown(player: Player) {
        disbandGroup(player)
    }
}