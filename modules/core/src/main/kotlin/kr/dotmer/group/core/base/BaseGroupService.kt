package kr.dotmer.group.core.base

import java.util.UUID
import java.util.regex.Pattern
import kr.dotmer.group.api.group.BaseGroup
import kr.dotmer.group.api.repository.GroupRepository
import kr.dotmer.group.api.service.GroupService

abstract class BaseGroupService<Group : BaseGroup, GroupEntity : BaseGroupEntity>(
    private val groupRepository: GroupRepository<GroupEntity>
) : GroupService<Group> {
    object Constants {
        val GROUP_NAME_PATTERN = Pattern.compile("^[가-힣a-zA-Z0-9]*$")!!
    }

    override suspend fun findAll(): List<Group> {
        return groupRepository.findAll().map { it.toDomain() }
    }

    override suspend fun findByUniqueId(uniqueId: UUID): Group? {
        return groupRepository.findByUniqueId(uniqueId)?.toDomain()
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
            this.uniqueId = UUID.randomUUID()
            this.name = name
            this.leader = leader
            this.level = 1u
        }.toDomain()

        onGroupCreated(group)

        return group
    }

    override suspend fun disband(group: Group) {
        groupRepository.delete(group.id)
        onGroupDisbanded(group)
    }

    override suspend fun addMember(group: Group, memberUniqueId: UUID) {
        groupRepository.addMember(group.id, memberUniqueId)
        onGroupMemberAdded(group, memberUniqueId)
    }

    override suspend fun removeMember(group: Group, memberUniqueId: UUID) {
        groupRepository.removeMember(group.id, memberUniqueId)
        onGroupMemberRemoved(group, memberUniqueId)
    }

    override fun validateGroupName(name: String): Boolean {
        return (name.length in 2..10 && Constants.GROUP_NAME_PATTERN.matcher(name).matches())
    }

    override suspend fun levelUp(group: Group) {
        groupRepository.update(group.id) {
            this.level++
        }
    }

    abstract fun onGroupCreated(group: Group)

//    abstract fun onGroupDisbanded(group: Group) TODO: 이벤트 생성

    abstract fun onGroupMemberAdded(group: Group, memberUniqueId: UUID)

    abstract fun onGroupMemberRemoved(group: Group, memberUniqueId: UUID)

    abstract fun onGroupDisbanded(group: Group)

    abstract suspend fun GroupEntity.toDomain(): Group
}