package gg.obsidian.playerpositions

import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import java.io.File
import java.util.*

class Plugin : JavaPlugin(), Listener {

    val config = Configuration(this)
    val eventListener = EventListener(this)

    val lastSeen = HashMap<Player, String>()
    var hasUpdated = true

    override fun onEnable() {
        val configFile = File(dataFolder, "config.yml")
        if (!configFile.exists()) {
            getConfig().options().copyDefaults(true)
            saveConfig()
        }

        config.load()

        server.scheduler.scheduleSyncRepeatingTask(this, TimerTask(this), config.interval, config.interval)
        server.pluginManager.registerEvents(eventListener, this)
    }

    fun updatePlayer(player: Player) {
        hasUpdated = true
        synchronized(lastSeen) {
            lastSeen.put(player, config.dateFormat.format(Date()))
        }
    }

    fun removePlayer(player: Player) {
        hasUpdated = true
        synchronized(lastSeen) {
            lastSeen.remove(player)
        }
    }

    fun getJSON(): JSONArray? {
        if (!hasUpdated) return null

        hasUpdated = false

        val jsonList = JSONArray()
        var out: JSONObject

        if (config.WRITE_SPAWN) {
            for (world in server.worlds) {
                out = JSONObject()
                out.put("msg", "Spawn")
                out.put("id", 1)
                out.put("world", world.name)
                out.put("x", world.spawnLocation.x)
                out.put("y", world.spawnLocation.y)
                out.put("z", world.spawnLocation.z)
                jsonList.add(out)
            }
        }

        for (player in server.onlinePlayers) {
            out = JSONObject()
            out.put("msg", player.name)
            out.put("id", 4)
            out.put("world", player.location.world.name)
            out.put("x", player.location.x)
            out.put("y", player.location.y)
            out.put("z", player.location.z)
            out.put("uuid", player.uniqueId.toString())

            synchronized(lastSeen) {
                val s = lastSeen.get(player)
                if (s != null) out.put("timestamp", s)
            }

            jsonList.add(out)
        }

        return jsonList
    }
}
