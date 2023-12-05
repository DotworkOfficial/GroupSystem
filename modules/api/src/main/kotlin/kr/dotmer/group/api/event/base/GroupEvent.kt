package kr.dotmer.group.api.event.base

import kr.dotmer.group.api.group.BaseGroup
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

abstract class GroupEvent<T : BaseGroup>(
    val group: T
) : Event(true) {
    companion object {
        private val HANDLERS = HandlerList()

        @JvmStatic
        @Suppress("unused")
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }
}