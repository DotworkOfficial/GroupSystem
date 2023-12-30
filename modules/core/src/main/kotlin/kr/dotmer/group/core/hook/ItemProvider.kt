package kr.dotmer.group.core.hook

import org.bukkit.entity.Player

interface ItemProvider {
    suspend fun hasItem(player: Player, category: String, name: String, amount: Int): Boolean

    suspend fun giveItem(player: Player, category: String, name: String, amount: Int)

    suspend fun takeItem(player: Player, category: String, name: String, amount: Int)
}