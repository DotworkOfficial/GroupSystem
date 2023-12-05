package kr.dotmer.group.core.base

import kr.dotmer.group.api.repository.GroupRepository
import kr.hqservice.framework.database.extension.findByIdForUpdate
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*

abstract class BaseGroupRepository<GroupEntity : BaseGroupEntity, UserEntity : BaseUserEntity<GroupEntity>>(
    private val groupEntityClass: LongEntityClass<GroupEntity>,
    private val userEntityClass: LongEntityClass<UserEntity>,
    private val groupTable: BaseGroupTable,
    private val userTable: BaseUserTable,
) : GroupRepository<GroupEntity> {
    override suspend fun findByUniqueId(uniqueId: UUID): GroupEntity? {
        return newSuspendedTransaction {
            groupEntityClass
                .find { groupTable.uniqueId eq uniqueId }
                .firstOrNull()
        }
    }

    override suspend fun findById(id: Long): GroupEntity? {
        return newSuspendedTransaction { groupEntityClass.findById(id) }
    }

    override suspend fun findByName(name: String): GroupEntity? {
        return newSuspendedTransaction {
            groupEntityClass
                .find { groupTable.name eq name }
                .firstOrNull()
        }
    }

    override suspend fun findPlayerGroup(memberUniqueId: UUID): GroupEntity? {
        return newSuspendedTransaction {
            userEntityClass
                .find { userTable.uniqueId eq memberUniqueId }
                .firstOrNull()
                ?.group
        }
    }

    override suspend fun findAll(): List<GroupEntity> {
        return newSuspendedTransaction {
            groupEntityClass
                .all()
                .toList()
        }
    }

    override suspend fun new(entity: GroupEntity.() -> Unit): GroupEntity {
        return newSuspendedTransaction {
            groupEntityClass.new(entity)
        }
    }

    override suspend fun update(id: Long, entity: GroupEntity.() -> Unit) {
        newSuspendedTransaction {
            groupEntityClass
                .findByIdForUpdate(id) // TODO: findById vs findByIdForUpdate
                ?.apply(entity)
        }
    }

    override suspend fun delete(id: Long) {
        newSuspendedTransaction {
            groupEntityClass
                .findById(id)
                ?.delete()
        }
    }

    override suspend fun addMember(id: Long, memberUniqueId: UUID) {
        newSuspendedTransaction {
            userEntityClass.new {
                this.group = groupEntityClass.findById(id)!!
                this.uniqueId = memberUniqueId
            }
        }
    }

    override suspend fun removeMember(id: Long, memberUniqueId: UUID) {
        newSuspendedTransaction {
            userEntityClass
                .find { userTable.uniqueId eq memberUniqueId }
                .firstOrNull()
                ?.delete()
        }
    }

    override suspend fun getMembers(id: Long): List<UUID> {
        return newSuspendedTransaction {
            userEntityClass
                .find { userTable.group eq id }
                .map { it.uniqueId }
        }
    }
}