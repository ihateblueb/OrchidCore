package site.remlit.blossom.service

import site.remlit.blossom.Main
import site.remlit.blossom.Main.Companion.configPath
import site.remlit.blossom.util.jsonConfig
import kotlin.io.path.writeText

object ConfigService {
    fun upgrade() {
        if (Main.config.version <= 0)
            migration1()

        writeCurrent()
    }

    fun writeCurrent() = configPath.writeText(jsonConfig.encodeToString(Main.config))

    private fun migration1() {
        Main.config.chat.format.replace("%player%", "%username%")
        Main.config.discord.joinFormat.replace("%player%", "%username%")
        Main.config.discord.leaveFormat.replace("%player%", "%username%")
        Main.config.discord.hytaleToDiscordFormat.replace("%player%", "%username%")
        Main.config.discord.discordToHytaleFormat.replace("%player%", "%username%")
    }
}