package kr.dotmer.group.api.event

import kr.dotmer.group.api.group.BaseGroup

abstract class GroupCreateEvent<T : BaseGroup>(group: T) : GroupEvent<T>(group) {
}