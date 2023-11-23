package kr.dotmer.group.core.guild.persistence

import kr.dotmer.group.core.base.BaseGroupRepository
import kr.hqservice.framework.database.repository.Repository

@Repository
class GuildRepository : BaseGroupRepository<GuildEntity, GuildUserEntity>(
    GuildEntity,
    GuildUserEntity,
    GuildTable,
    GuildUserTable,
)