package kr.dotmer.group.core.guild

import kr.dotmer.group.api.event.guild.GuildCreateEvent
import kr.dotmer.group.api.event.guild.GuildPlayerJoinEvent
import kr.dotmer.group.api.event.guild.GuildPlayerLeaveEvent
import kr.dotmer.group.core.base.BaseGroupService
import kr.dotmer.group.core.guild.domain.GuildImpl
import kr.dotmer.group.core.guild.persistence.GuildEntity
import kr.dotmer.group.core.guild.persistence.GuildRepository
import kr.hqservice.framework.global.core.component.Qualifier
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.Bukkit
import java.util.*

@Service
@Qualifier("group.guild")
class GuildService(
    private val guildRepository: GuildRepository
) : BaseGroupService<GuildImpl, GuildEntity>(guildRepository) {
    override suspend fun GuildEntity.toDomain(): GuildImpl {
        return GuildImpl(
            id = this.id.value,
            uniqueId = this.uniqueId,
            name = this.name,
            leader = this.leader,
            level = this.level,
            members = guildRepository.getMembers(this.id.value)
        )
    }

    override fun onGroupCreated(group: GuildImpl) {
        GuildCreateEvent(group).let {
            Bukkit.getServer().pluginManager.callEvent(it)
        }
    }

    override fun onGroupMemberAdded(group: GuildImpl, memberUniqueId: UUID) {
        GuildPlayerJoinEvent(group, memberUniqueId).let {
            Bukkit.getServer().pluginManager.callEvent(it)
        }
    }

    override fun onGroupMemberRemoved(group: GuildImpl, memberUniqueId: UUID) {
        GuildPlayerLeaveEvent(group, memberUniqueId).let {
            Bukkit.getServer().pluginManager.callEvent(it)
        }
    }
}