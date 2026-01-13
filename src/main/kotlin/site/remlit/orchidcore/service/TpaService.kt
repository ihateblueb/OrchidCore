package site.remlit.orchidcore.service

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.Universe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import site.remlit.orchidcore.Coroutines
import site.remlit.orchidcore.exception.GracefulException
import site.remlit.orchidcore.util.sendMessage
import java.util.UUID
import kotlin.time.Duration.Companion.seconds

object TpaService {
    /**
     * Map of pending TPAs where first is requesting player, and second is destination
     * */
    val pendingTpas = mutableMapOf<UUID, UUID>()

    /**
     * Map of pending TPAs where first is requesting destination player, and second is target
     * */
    val pendingTpaHeres = mutableMapOf<UUID, UUID>()

    fun appendTpa(from: UUID, to: UUID, here: Boolean = false) {
        /*
        if (from == to)
            throw GracefulException("You cannot teleport to yourself")
            */

        if (!pendingTpas.filter { (k, v) -> v == to }.isEmpty())
            throw GracefulException("This player already has someone trying to teleport to them")

        if (pendingTpas.contains(from))
            throw GracefulException("You already have an outgoing teleport request")

        pendingTpas[from] = to

        val sender = Universe.get().players.first { it.uuid == from }
        val target = Universe.get().players.first { it.uuid == to }

        if (sender.worldUuid != target.worldUuid)
            throw GracefulException("You are not in the same world")

        sender.sendMessage("${sender.username} has requested to teleport to you. You can accept with /tpaccept." +
                " This will expire in 30 seconds.")

        Coroutines.sharedScope.launch {
            delay(30.seconds)
            if (pendingTpas.contains(from)) {
                pendingTpas.remove(from)

                sender.sendMessage("Teleportation request to ${target.username} expired.")
                target.sendMessage("Teleportation request from ${sender.username} expired.")
            }
        }
    }

    fun acceptTpa(to: UUID, here: Boolean = false) {
        if (pendingTpas.filter { (k, v) -> v == to }.isEmpty())
            throw GracefulException("No pending teleportation requests")

        pendingTpas.filter { (k, v) -> v == to }
            .forEach { (k, v) ->
                val sender = Universe.get().players.first { it.uuid == k }
                val target = Universe.get().players.first { it.uuid == v }

                sender.sendMessage("${sender.username} accepted your teleportation request, you'll be teleported" +
                        " in 3 seconds.")
                target.sendMessage("Teleporting ${sender.username} to you.")

                val targetWorld = Universe.get().getWorld(target.worldUuid ?: throw GracefulException("Something went wrong"))
                    ?: throw GracefulException("Something went wrong")

                Coroutines.sharedScope.launch {
                    delay(3.seconds)
                    sender.updatePosition(targetWorld, target.transform, sender.headRotation)
                }

                pendingTpas.remove(k, v)
            }
    }

    fun denyTpa(to: UUID, here: Boolean = false) {
        if (pendingTpas.filter { (k, v) -> v == to }.isEmpty())
            throw GracefulException("No pending teleportation requests")

        pendingTpas.filter { (k, v) -> v == to }
            .forEach { (k, v) ->
                val sender = Universe.get().players.first { it.uuid == k }
                val target = Universe.get().players.first { it.uuid == v }

                sender.sendMessage("${sender.username} denied your teleportation request.")
                target.sendMessage("Denied teleportation request from ${sender.username}.")

                pendingTpas.remove(k, v)
            }
    }
}