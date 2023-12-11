package kr.dotmer.group.core.town

import java.util.UUID
import kr.dotmer.group.api.event.town.TownCreateEvent
import kr.dotmer.group.api.event.town.TownDisbandEvent
import kr.dotmer.group.api.event.town.TownPlayerJoinEvent
import kr.dotmer.group.api.event.town.TownPlayerLeaveEvent
import kr.dotmer.group.core.base.BaseGroupService
import kr.dotmer.group.core.town.domain.TownImpl
import kr.dotmer.group.core.town.persistence.TownEntity
import kr.dotmer.group.core.town.persistence.TownRepository
import kr.hqservice.framework.global.core.component.Qualifier
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.Bukkit

@Service
@Qualifier("group.town")
class TownService(
    private val townRepository: TownRepository
) : BaseGroupService<TownImpl, TownEntity>(townRepository) {
    override suspend fun TownEntity.toDomain(): TownImpl {
        return TownImpl(
            id = this.id.value,
            uniqueId = this.uniqueId,
            name = this.name,
            leader = this.leader,
            level = this.level,
            members = townRepository.getMembers(this.id.value)
        )
    }

    override fun onGroupCreated(group: TownImpl) {
        TownCreateEvent(group).let {
            Bukkit.getServer().pluginManager.callEvent(it)
        }
    }

    override fun onGroupMemberAdded(group: TownImpl, memberUniqueId: UUID) {
        TownPlayerJoinEvent(group, memberUniqueId).let {
            Bukkit.getServer().pluginManager.callEvent(it)
        }
    }

    override fun onGroupMemberRemoved(group: TownImpl, memberUniqueId: UUID) {
        TownPlayerLeaveEvent(group, memberUniqueId).let {
            Bukkit.getServer().pluginManager.callEvent(it)
        }
    }

    override fun onGroupDisbanded(group: TownImpl) {
        TownDisbandEvent(group).let {
            Bukkit.getServer().pluginManager.callEvent(it)
        }
    }
}
