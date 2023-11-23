package kr.dotmer.group.core.guild.persistence

import kr.dotmer.group.core.base.BaseUserEntity
import kr.dotmer.group.core.base.BaseUserTable
import kr.hqservice.framework.database.component.Table
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GuildUserEntity(
    id: EntityID<Long>
) : BaseUserEntity<GuildEntity>(id) {
    companion object : LongEntityClass<GuildUserEntity>(GuildUserTable)

    override var uniqueId by GuildUserTable.uniqueId
    override var group by GuildEntity referencedOn GuildUserTable.group
}

@Table
object GuildUserTable : BaseUserTable("group_guild_user_table", GuildTable)