package kr.dotmer.group.core.base

import kr.dotmer.group.api.group.BaseGroup
import java.util.*

data class Invitation<T : BaseGroup>(
    val inviter: UUID,
    val group: T,
)