package kr.dotmer.group.core.base

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.dotmer.group.api.group.BaseGroup
import kr.dotmer.group.api.service.GroupService
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

abstract class BaseGroupPlayerService<T : BaseGroup>(
    private val plugin: HQBukkitPlugin,
    private val groupService: GroupService<T>
) : Listener {
    private val cacheMap = ConcurrentHashMap<UUID, Long>()

    fun getPlayerGroupId(uniqueId: UUID): Long? {
        return cacheMap[uniqueId]
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        plugin.launch(Dispatchers.IO) {
            val player = event.player
            val group = groupService.findPlayerGroup(player.uniqueId)

            if (group != null) {
                cacheMap[player.uniqueId] = group.id
            }
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        cacheMap.remove(event.player.uniqueId)
    }
}