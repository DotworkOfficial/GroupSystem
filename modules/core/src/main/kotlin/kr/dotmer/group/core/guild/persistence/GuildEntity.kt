package kr.dotmer.group.core.guild.persistence

import kr.dotmer.group.core.base.BaseGroupEntity
import kr.dotmer.group.core.base.BaseGroupTable
import kr.hqservice.framework.database.component.Table
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GuildEntity(
    id: EntityID<Long>
) : BaseGroupEntity(id) {
    companion object : LongEntityClass<GuildEntity>(GuildTable)

    override var uniqueId by GuildTable.uniqueId
    override var name by GuildTable.name
    override var leader by GuildTable.leader
    override var level by GuildTable.level
}

@Table
object GuildTable : BaseGroupTable("group_guild_table")