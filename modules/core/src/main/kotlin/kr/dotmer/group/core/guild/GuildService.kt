package kr.dotmer.group.core.guild

import kr.dotmer.group.core.base.BaseGroupService
import kr.dotmer.group.core.guild.domain.GuildImpl
import kr.dotmer.group.core.guild.persistence.GuildEntity
import kr.dotmer.group.core.guild.persistence.GuildRepository
import kr.hqservice.framework.global.core.component.Qualifier
import kr.hqservice.framework.global.core.component.Service

@Service
@Qualifier("group.guild")
class GuildService(
    private val guildRepository: GuildRepository
) : BaseGroupService<GuildImpl, GuildEntity>(guildRepository) {
    override suspend fun GuildEntity.toDomain(): GuildImpl {
        return GuildImpl(
            id = this.id.value,
            name = this.name,
            leader = this.leader,
            level = this.level,
            members = guildRepository.getMembers(this.id.value)
        )
    }
}