package kr.dotmer.group.core

import kr.dotmer.group.core.guild.GuildPlayerService
import kr.dotmer.group.core.settings.Settings
import kr.dotmer.group.core.town.TownPlayerService
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.global.core.component.HQModule
import org.bukkit.Bukkit
import org.mineacademy.chatcontrol.api.Party

class GroupSystemBootstrap(
    private val plugin: HQBukkitPlugin,
    private val guildPlayerService: GuildPlayerService,
    private val townPlayerService: TownPlayerService,
) : HQModule {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(guildPlayerService, plugin)

        Settings.reload()
        registerGroupChannelsToChatControl()
    }

    private fun registerGroupChannelsToChatControl() {
        Party.register("groupsystem-guild") { receiver, sender ->
            val receiverGuildId = guildPlayerService.getPlayerGroupId(receiver.uniqueId) ?: return@register false
            val senderGuildId = guildPlayerService.getPlayerGroupId(sender.uniqueId) ?: return@register false

            return@register receiverGuildId == senderGuildId
        }

        Party.register("groupsystem-town") { receiver, sender ->
            val receiverTownId = townPlayerService.getPlayerGroupId(receiver.uniqueId) ?: return@register false
            val senderTownId = townPlayerService.getPlayerGroupId(sender.uniqueId) ?: return@register false

            return@register receiverTownId == senderTownId
        }
    }
}