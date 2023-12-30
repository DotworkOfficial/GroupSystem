package kr.dotmer.group.core.hook

import kr.hqservice.economy.api.Currency
import kr.hqservice.economy.api.registry.CurrencyRegistry
import kr.hqservice.economy.api.service.EconomyService
import kr.hqservice.framework.bukkit.core.component.registry.PluginDepend
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.entity.Player

@Component
@PluginDepend(["HQEconomy"])
class HQEconomyMoneyProvider(
    private val currencyRegistry: CurrencyRegistry,
    private val economyService: EconomyService
) {
    suspend fun provideMoney(name: String): HQEconomyMoney? {
        return currencyRegistry.findCurrencyByName(name)?.let { currency ->
            HQEconomyMoney(currency)
        }
    }

    inner class HQEconomyMoney(private val currency: Currency) {
        fun getId(): String {
            return "hqeconomy.${currency.name}"
        }

        fun getDisplay(): String? {
            return currency.displayName
        }

        suspend fun deposit(player: Player, amount: Long) {
            economyService.deposit(player.uniqueId, currency.name, amount)
        }

        suspend fun withdraw(player: Player, amount: Long) {
            economyService.withdraw(player.uniqueId, currency.name, amount)
        }

        suspend fun getBalance(player: Player): Long {
            return economyService.getBalance(player.uniqueId, currency.name).balance
        }
    }
}