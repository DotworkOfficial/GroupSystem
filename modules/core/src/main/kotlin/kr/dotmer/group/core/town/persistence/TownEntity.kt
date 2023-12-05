package kr.dotmer.group.core.town.persistence

import kr.dotmer.group.core.base.BaseGroupEntity
import kr.dotmer.group.core.base.BaseGroupTable
import kr.hqservice.framework.database.component.Table
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TownEntity(
    id: EntityID<Long>
) : BaseGroupEntity(id) {
    companion object : LongEntityClass<TownEntity>(TownTable)

    override var uniqueId by TownTable.uniqueId
    override var name by TownTable.name
    override var leader by TownTable.leader
    override var level by TownTable.level
}

@Table
object TownTable : BaseGroupTable("group_town_table")
