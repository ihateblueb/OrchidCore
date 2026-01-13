package site.remlit.orchidcore

import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import site.remlit.orchidcore.command.*

class Main(init: JavaPluginInit) : JavaPlugin(init) {
    override fun setup() {
        commandRegistry.registerCommand(TpacceptCommand())
        commandRegistry.registerCommand(TpaCommand())
        commandRegistry.registerCommand(TpdenyCommand())
    }
}