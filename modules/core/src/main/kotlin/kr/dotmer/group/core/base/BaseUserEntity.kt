package kr.dotmer.group.core.base

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

abstract class BaseUserEntity<GroupEntity : BaseGroupEntity>(
    id: EntityID<Long>
) : LongEntity(id) {
    abstract var uniqueId: UUID
    abstract var group: GroupEntity
}

abstract class BaseUserTable(
    name: String,
    groupTable: BaseGroupTable
) : LongIdTable(name) {
    val uniqueId = uuid("unique_id").uniqueIndex()
    val group = reference("group", groupTable, ReferenceOption.CASCADE)
}