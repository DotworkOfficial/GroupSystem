package kr.dotmer.group.core.town

import kr.dotmer.group.core.base.BaseGroupService
import kr.dotmer.group.core.town.domain.TownImpl
import kr.dotmer.group.core.town.event.TownCreateEvent
import kr.dotmer.group.core.town.event.TownPlayerJoinEvent
import kr.dotmer.group.core.town.event.TownPlayerLeaveEvent
import kr.dotmer.group.core.town.persistence.TownEntity
import kr.dotmer.group.core.town.persistence.TownRepository
import kr.hqservice.framework.global.core.component.Qualifier
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.Bukkit
import java.util.*

@Service
@Qualifier("group.town")
class TownService(
    private val townRepository: TownRepository
) : BaseGroupService<TownImpl, TownEntity>(townRepository) {
    override suspend fun TownEntity.toDomain(): TownImpl {
        return TownImpl(
            id = this.id.value,
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
}
