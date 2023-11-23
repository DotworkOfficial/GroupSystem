package kr.dotmer.group.core.town.persistence

import kr.dotmer.group.core.base.BaseUserEntity
import kr.dotmer.group.core.base.BaseUserTable
import kr.hqservice.framework.database.component.Table
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class TownUserEntity(
    id: EntityID<Long>
) : BaseUserEntity<TownEntity>(id) {
    companion object : LongEntityClass<TownUserEntity>(TownUserTable)

    override var uniqueId: UUID by TownUserTable.uniqueId
    override var group: TownEntity by TownEntity referencedOn TownUserTable.group
}

@Table
object TownUserTable : BaseUserTable("group_town_user_table", TownTable)
