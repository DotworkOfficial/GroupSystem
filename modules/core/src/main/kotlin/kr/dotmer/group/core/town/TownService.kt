package kr.dotmer.group.core.town

import kr.dotmer.group.core.base.BaseGroupService
import kr.dotmer.group.core.town.domain.TownImpl
import kr.dotmer.group.core.town.persistence.TownEntity
import kr.dotmer.group.core.town.persistence.TownRepository
import kr.hqservice.framework.global.core.component.Qualifier
import kr.hqservice.framework.global.core.component.Service

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
}
