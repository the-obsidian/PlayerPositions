package gg.obsidian.playerpositions

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

class TimerTask(val plugin: Plugin): Runnable {
    override fun run() {
        val jsonList = plugin.getJSON() ?: return

        try {
            val writer = PrintWriter(BufferedWriter(FileWriter(plugin.config.OUTPUT_FILE)))
            writer.print(jsonList)
            writer.close()
        } catch (e: IOException) {
            plugin.logger.severe("Unable to write to " + plugin.config.OUTPUT_FILE + ": " + e.message)
            e.printStackTrace()
        }
    }
}
