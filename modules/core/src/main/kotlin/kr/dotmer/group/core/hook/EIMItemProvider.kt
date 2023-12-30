package kr.dotmer.group.core.hook

import kr.elroy.itemmanager.api.domain.ElroyItem
import kr.elroy.itemmanager.api.service.ElroyItemService
import kr.hqservice.framework.bukkit.core.component.registry.PluginDepend
import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.global.core.component.Qualifier
import org.bukkit.entity.Player

@Bean
@PluginDepend(["ElroyItemManager"])
@Qualifier("group.provider.eim")
class EIMItemProvider(
    private val elroyItemService: ElroyItemService
) : ItemProvider {
    override suspend fun hasItem(player: Player, category: String, name: String, amount: Int): Boolean {
        val elroyItem = findElroyItem(category, name)!!

        return elroyItemService.hasElroyItem(player, elroyItem, amount)
    }

    override suspend fun giveItem(player: Player, category: String, name: String, amount: Int) {
        val elroyItem = findElroyItem(category, name)!!

        elroyItemService.giveElroyItem(elroyItem, amount, player, "GroupSystem")
    }

    override suspend fun takeItem(player: Player, category: String, name: String, amount: Int) {
        val elroyItem = findElroyItem(category, name)!!

        elroyItemService.takeElroyItem(player, elroyItem, amount, "GroupSystem")
    }

    private suspend fun findElroyItem(category: String, name: String): ElroyItem? {
        return elroyItemService.findElroyItem(category, name)
    }
}