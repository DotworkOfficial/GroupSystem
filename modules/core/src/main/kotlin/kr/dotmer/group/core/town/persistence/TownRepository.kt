package kr.dotmer.group.core.town.persistence

import kr.dotmer.group.core.base.BaseGroupRepository
import kr.hqservice.framework.database.repository.Repository

@Repository
class TownRepository : BaseGroupRepository<TownEntity, TownUserEntity>(
    TownEntity,
    TownUserEntity,
    TownTable,
    TownUserTable,
)
