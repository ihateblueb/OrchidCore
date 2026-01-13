package site.remlit.orchidcore

import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit

class Main(init: JavaPluginInit) : JavaPlugin(init) {
    override fun setup() {
        this.logger.atInfo().log("Initializing plugin...")
    }
}