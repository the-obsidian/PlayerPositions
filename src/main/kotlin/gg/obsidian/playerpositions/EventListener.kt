package gg.obsidian.playerpositions

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent

class EventListener(val plugin: Plugin): Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerMove(event: PlayerMoveEvent) {
        plugin.updatePlayer(event.player)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerLogin(event: PlayerLoginEvent) {
        plugin.updatePlayer(event.player)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerTeleport(event: PlayerMoveEvent) {
        plugin.updatePlayer(event.player)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerQuit(event: PlayerQuitEvent) {
        plugin.removePlayer(event.player)
    }
}
