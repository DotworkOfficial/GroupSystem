package kr.dotmer.group.core

import kr.dotmer.group.core.settings.Settings
import kr.hqservice.framework.bukkit.core.extension.sendColorizedMessage
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender

@Suppress("unused")
@Command(label = "groupadmin", isOp = true)
class GroupAdminCommand {
    @CommandExecutor(label = "reload")
    fun reload(sender: CommandSender) {
        val start = System.currentTimeMillis()
        Settings.reload()
        val end = System.currentTimeMillis()

        sender.sendColorizedMessage("&aReloaded settings.yml in ${end - start}ms")
    }
}