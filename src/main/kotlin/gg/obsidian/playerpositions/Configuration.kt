package gg.obsidian.playerpositions

import java.text.SimpleDateFormat

class Configuration(val plugin: Plugin) {

    var SAVE_INTERVAL: Long = 3000
    var OUTPUT_FILE = "world/markers.json"
    var WRITE_SPAWN = false
    var DATE_FORMAT = "yyyyMMdd HH:mm:ss"

    var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyyMMdd HH:mm:ss")
    var interval: Long = SAVE_INTERVAL / 50

    fun load() {
        plugin.reloadConfig()

        SAVE_INTERVAL = plugin.getConfig().getLong("save-interval")
        OUTPUT_FILE = plugin.getConfig().getString("output-file")
        WRITE_SPAWN = plugin.getConfig().getBoolean("write-spawn")
        DATE_FORMAT = plugin.getConfig().getString("date-format")

        try {
            dateFormat = SimpleDateFormat(DATE_FORMAT)
        } catch (e: Exception) {
            plugin.logger.warning("Invalid date format, defaulting to yyyyMMdd HH:mm:ss")
            dateFormat = SimpleDateFormat("yyyyMMdd HH:mm:ss")
        }

        // Convert to 20 ticks-per-second
        interval = SAVE_INTERVAL / 50
    }
}
