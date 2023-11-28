package kr.dotmer.group.core.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.dotmer.group.api.group.BaseGroup
import kr.dotmer.group.api.group.GroupType
import kr.dotmer.group.api.service.GroupService
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.coroutine.extension.BukkitAsync
import kr.hqservice.framework.bukkit.core.extension.sendColorizedMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

abstract class BaseGroupCommand<T : BaseGroup>(
    private val groupService: GroupService<T>,
    private val groupType: GroupType,
    private val plugin: HQBukkitPlugin,
) {
    private val invitationMap: ConcurrentHashMap<Player, Invitation<T>> = ConcurrentHashMap()

    // TODO: 생성 비용 설정 필요
    open suspend fun createGroup(player: Player, name: String) {
        val group = groupService.create(name, player.uniqueId)
        groupService.addMember(group, player.uniqueId)
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

    open suspend fun disbandGroup(player: Player) {
        val playerGroup = groupService.findPlayerGroup(player.uniqueId)

        if (playerGroup == null) {
            player.sendColorizedMessage("&c어떤 곳에도 속해있지 않습니다.")
            return
        }

        if (!playerGroup.isLeader(player.uniqueId)) {
            player.sendColorizedMessage("&c리더만 해체할 수 있습니다.")
            return
        }

        groupService.disband(playerGroup)
        player.sendColorizedMessage("&a성공적으로 해체하였습니다.")
    }
}