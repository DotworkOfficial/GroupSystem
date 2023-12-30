package kr.dotmer.group.core.base

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.dotmer.group.api.group.BaseGroup
import kr.dotmer.group.api.group.GroupType
import kr.dotmer.group.api.service.GroupService
import kr.dotmer.group.core.hook.EconomyService
import kr.dotmer.group.core.hook.ItemProvider
import kr.dotmer.group.core.settings.GroupSettings
import kr.dotmer.group.core.settings.Settings
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.coroutine.extension.BukkitAsync
import kr.hqservice.framework.bukkit.core.extension.colorize
import kr.hqservice.framework.bukkit.core.extension.sendColorizedMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player

abstract class BaseGroupCommand<T : BaseGroup>(
    private val groupService: GroupService<T>,
    private val groupType: GroupType,
    private val economyService: EconomyService,
    private val itemProvider: ItemProvider,
    private val groupSettings: GroupSettings,
    private val plugin: HQBukkitPlugin,
) {
    private val invitationMap: ConcurrentHashMap<Player, Invitation<T>> = ConcurrentHashMap()
    private val rejoinCooldownPlayers = mutableListOf<UUID>()

    open suspend fun createGroup(player: Player, name: String) {
        if (groupService.findPlayerGroup(player.uniqueId) != null) {
            player.sendColorizedMessage("&c이미 다른 곳에 속해있습니다.")
            return
        }

        if (groupService.findByName(name) != null) {
            player.sendColorizedMessage("&c이미 존재하는 이름입니다.")
            return
        }

        if (!groupService.validateGroupName(name)) {
            player.sendColorizedMessage("&c이름은 2~10자의 한글, 영문, 숫자만 가능합니다.")
            return
        }

        val balance = economyService.getBalance(player, "센")

        if (balance < 5_000_000L) {
            player.sendColorizedMessage("&c센이 부족합니다.")
            return
        }

        economyService.withdraw(player, 5_000_000L, "센")

        val group = groupService.create(name, player.uniqueId)
        groupService.addMember(group, player.uniqueId)

        player.sendColorizedMessage("&a성공적으로 생성하였습니다.")
        Bukkit.broadcastMessage("&a${player.name}님이 ${group.name} ${groupType.koreanName}을(를) 생성하였습니다.".colorize())
    }

    open suspend fun kickMember(player: Player, targetName: String) {
        val group = groupService.findPlayerGroup(player.uniqueId)

        if (group == null) {
            player.sendColorizedMessage("&c어떤 곳에도 속해있지 않습니다.")
            return
        }

        if (!group.isLeader(player.uniqueId)) {
            player.sendColorizedMessage("&c리더만 추방할 수 있습니다.")
            return
        }

        @Suppress("DEPRECATION")
        val target = Bukkit.getOfflinePlayer(targetName)

        if (!target.hasPlayedBefore()) {
            player.sendColorizedMessage("&c존재하지 않는 유저입니다.")
            return
        }

        if (!group.isMember(target.uniqueId)) {
            player.sendColorizedMessage("&c해당 유저는 ${group.name} ${groupType.koreanName}에 속해있지 않습니다.")
            return
        }

        groupService.removeMember(group, target.uniqueId)
        rejoinCooldownPlayers.add(target.uniqueId)

        player.sendColorizedMessage("&a성공적으로 추방하였습니다.")
        target.player?.sendColorizedMessage("&c${player.name}님에 의해 추방되었습니다.")
    }

    open suspend fun inviteMember(player: Player, target: Player) {
        val group = groupService.findPlayerGroup(player.uniqueId)

        if (group == null) {
            player.sendColorizedMessage("&c어떤 곳에도 속해있지 않습니다.")
            return
        }

        if (!group.isLeader(player.uniqueId)) {
            player.sendColorizedMessage("&c리더만 초대할 수 있습니다.")
            return
        }

        if (group.isFull()) {
            player.sendColorizedMessage("&c인원이 가득 찼습니다.")
            return
        }

        if (rejoinCooldownPlayers.contains(target.uniqueId)) {
            player.sendColorizedMessage("&c해당 유저는 다음 리붓까지 가입할 수 없습니다.")
            return
        }

        if (groupService.findPlayerGroup(target.uniqueId) != null) {
            player.sendColorizedMessage("&c이미 다른 곳에 속해있는 유저입니다.")
            return
        }

        if (invitationMap.containsKey(target)) {
            player.sendColorizedMessage("&c다른 요청을 처리중입니다.")
            return
        }

        invitationMap[target] = Invitation(player.uniqueId, group)
        player.sendColorizedMessage("&a${target.name}님에게 초대장을 보냈습니다.")
        target.sendColorizedMessage("&a${player.name}님으로부터 ${group.name} ${groupType.koreanName}의 초대장을 받았습니다.")

        // 15초 자동 거절
        plugin.launch(Dispatchers.BukkitAsync) {
            delay(15 * 1000)

            if (invitationMap.remove(player) != null) {
                player.sendColorizedMessage("&c초대장이 만료되었습니다.")
                target.sendColorizedMessage("&c초대장이 만료되었습니다.")
            }
        }
    }

    open suspend fun leaveGroup(player: Player) {
        val group = groupService.findPlayerGroup(player.uniqueId)

        if (group == null) {
            player.sendColorizedMessage("&c어떤 곳에도 속해있지 않습니다.")
            return
        }

        if (group.isLeader(player.uniqueId)) {
            player.sendColorizedMessage("&c리더는 탈퇴할 수 없습니다.")
            return
        }

        groupService.removeMember(group, player.uniqueId)
        rejoinCooldownPlayers.add(player.uniqueId)

        player.sendColorizedMessage("&a성공적으로 탈퇴하였습니다.")
    }

    open suspend fun levelUpGroup(player: Player) {
        val group = groupService.findPlayerGroup(player.uniqueId)

        if (group == null) {
            player.sendColorizedMessage("&c어떤 곳에도 속해있지 않습니다.")
            return
        }

        if (!group.isLeader(player.uniqueId)) {
            player.sendColorizedMessage("&c리더만 진급할 수 있습니다.")
            return
        }

        if (group.level >= groupSettings.getMaxLevel().toUInt()) {
            player.sendColorizedMessage("&c더 이상 진급할 수 없습니다.")
            return
        }

        val category = Settings.LevelUpItem.CATEGORY
        val name = Settings.LevelUpItem.NAME

        if (!itemProvider.hasItem(player, category, name, 5 * group.level.toInt())) {
            player.sendColorizedMessage("&c번영의서가 부족합니다. (필요한 개수: ${5 * group.level.toInt()})")
            return
        }

        itemProvider.takeItem(player, category, name, 5 * group.level.toInt())
        groupService.levelUp(group)

        player.sendColorizedMessage("&a성공적으로 진급했습니다.")
    }

    open suspend fun acceptInvite(player: Player) {
        val invitation = invitationMap.remove(player)

        if (invitation == null) {
            player.sendColorizedMessage("&c응답할 초대가 없습니다.")
            return
        }

        if (invitation.group.isFull()) {
            player.sendColorizedMessage("&c인원이 가득 찼습니다.")
            return
        }

        groupService.addMember(invitation.group, player.uniqueId)

        player.sendColorizedMessage("&a성공적으로 가입하였습니다.")

        Bukkit.getOfflinePlayer(invitation.inviter)
            .player
            ?.sendColorizedMessage("&a${player.name}님이 가입하였습니다.")
    }

    open fun denyInvite(player: Player) {
        val invitation = invitationMap.remove(player)

        if (invitation == null) {
            player.sendColorizedMessage("&c응답할 초대가 없습니다.")
            return
        }

        player.sendColorizedMessage("&a초대를 거절하였습니다.")

        Bukkit.getOfflinePlayer(invitation.inviter)
            .player
            ?.sendColorizedMessage("&a${player.name}님이 초대를 거절하였습니다.")
    }

    open suspend fun disbandGroup(player: Player, name: String) {
        val playerGroup = groupService.findPlayerGroup(player.uniqueId)

        if (playerGroup == null) {
            player.sendColorizedMessage("&c어떤 곳에도 속해있지 않습니다.")
            return
        }

        if (!playerGroup.isLeader(player.uniqueId)) {
            player.sendColorizedMessage("&c리더만 해체할 수 있습니다.")
            return
        }

        if (name != playerGroup.name) {
            player.sendColorizedMessage("&c해체할 ${groupType.koreanName}의 이름이 일치하지 않습니다.")
            return
        }

        groupService.disband(playerGroup)
        player.sendColorizedMessage("&a성공적으로 해체하였습니다.")
    }

    open suspend fun groupInfo(player: Player) {
        val group = groupService.findPlayerGroup(player.uniqueId)

        if (group == null) {
            player.sendColorizedMessage("&c어떤 곳에도 속해있지 않습니다.")
            return
        }

        player.sendColorizedMessage("<s:9c9c83>&m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        player.sendColorizedMessage("&6리더: &f${Bukkit.getOfflinePlayer(group.leader).name}")
        player.sendColorizedMessage("&6레벨: &f${group.level}")
        player.sendColorizedMessage("&6멤버:")

        for (member in group.members) {
            val prefix = if (member == group.members.last()) " §7┗━§f" else " §7┣━§f"
            player.sendColorizedMessage("$prefix ${Bukkit.getOfflinePlayer(member).name}")
        }

        player.sendColorizedMessage("<s:9c9c83>&m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
    }
}