package kr.dotmer.group.core.base

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import java.util.*

abstract class BaseGroupEntity(
    id: EntityID<Long>
) : LongEntity(id) {
    abstract var uniqueId: UUID
    abstract var name: String
    abstract var leader: UUID
    abstract var level: UInt
}

abstract class BaseGroupTable(name: String) : LongIdTable(name) {
    val uniqueId = uuid("unique_id").uniqueIndex()
    val name = varchar("name", 16).uniqueIndex()
    val leader = uuid("leader")
    val level = uinteger("level")
}