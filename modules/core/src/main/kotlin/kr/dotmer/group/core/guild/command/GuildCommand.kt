package kr.dotmer.group.core.guild.command

import kr.dotmer.group.api.service.GroupService
import kr.dotmer.group.core.base.BaseGroupCommand
import kr.dotmer.group.core.guild.domain.GuildImpl
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import kr.hqservice.framework.global.core.component.Qualifier
import org.bukkit.entity.Player

@Command(label = "길드")
class GuildCommand(
    @Qualifier("group.guild") groupService: GroupService<GuildImpl>
) : BaseGroupCommand<GuildImpl>(groupService) {
    @CommandExecutor("생성", description = "길드를 생성합니다.")
    suspend fun create(player: Player, name: String) {
        createGroup(player, name)
    }

    @CommandExecutor("해체", description = "길드를 해체합니다.")
    suspend fun disband(player: Player) {
        disbandGroup(player)
    }
}