package site.remlit.orchidcore.util

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.universe.PlayerRef

fun CommandContext.sendMessage(message: String) {
    this.sendMessage(Message.raw(message))
}

fun PlayerRef.sendMessage(message: String) {
    this.sendMessage(Message.raw(message))
}