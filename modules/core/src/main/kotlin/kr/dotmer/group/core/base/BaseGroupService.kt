package kr.dotmer.group.core.base

import kr.dotmer.group.api.group.BaseGroup
import kr.dotmer.group.api.repository.GroupRepository
import kr.dotmer.group.api.service.GroupService
import java.util.*

abstract class BaseGroupService<Group : BaseGroup, GroupEntity : BaseGroupEntity>(
    private val groupRepository: GroupRepository<GroupEntity>
) : GroupService<Group> {
    override suspend fun findAll(): List<Group> {
        return groupRepository.findAll().map { it.toDomain() }
    }

    override suspend fun findByName(name: String): Group? {
        return groupRepository.findByName(name)?.toDomain()
    }

    override suspend fun findPlayerGroup(memberUniqueId: UUID): Group? {
        return groupRepository.findPlayerGroup(memberUniqueId)?.toDomain()
    }

    /**
     * 생성 후 리더를 멤버로 추가해야합니다.
     */
    override suspend fun create(name: String, leader: UUID): Group {
        val group = groupRepository.new {
            this.name = name
            this.leader = leader
            this.level = 1u
        }.toDomain()

        onGroupCreated(group)

        return group
    }

    override suspend fun disband(group: Group) {
        groupRepository.delete(group.id)
    }

    override suspend fun addMember(group: Group, memberUniqueId: UUID) {
        groupRepository.addMember(group.id, memberUniqueId)
        onGroupMemberAdded(group, memberUniqueId)
    }

    override suspend fun removeMember(group: Group, memberUniqueId: UUID) {
        groupRepository.removeMember(group.id, memberUniqueId)
        onGroupMemberRemoved(group, memberUniqueId)
    }

    abstract fun onGroupCreated(group: Group)

//    abstract fun onGroupDisbanded(group: Group) TODO: 이벤트 생성

    abstract fun onGroupMemberAdded(group: Group, memberUniqueId: UUID)

    abstract fun onGroupMemberRemoved(group: Group, memberUniqueId: UUID)

    abstract suspend fun GroupEntity.toDomain(): Group
}