package kr.dotmer.group.core.hook

import kr.hqservice.framework.global.core.component.Service
import org.bukkit.entity.Player

@Service
class EconomyService(
    private val hqEconomyMoneyProvider: HQEconomyMoneyProvider,
) {
    suspend fun withdraw(player: Player, amount: Long, currencyName: String) {
        val money = hqEconomyMoneyProvider.provideMoney(currencyName) ?: throw IllegalStateException("Money not found")

        money.withdraw(player, amount)
    }

    suspend fun deposit(player: Player, amount: Long, currencyName: String) {
        val money = hqEconomyMoneyProvider.provideMoney(currencyName) ?: throw IllegalStateException("Money not found")

        money.deposit(player, amount)
    }

    suspend fun getBalance(player: Player, currencyName: String): Long {
        val money = hqEconomyMoneyProvider.provideMoney(currencyName) ?: throw IllegalStateException("Money not found")

        return money.getBalance(player)
    }
}