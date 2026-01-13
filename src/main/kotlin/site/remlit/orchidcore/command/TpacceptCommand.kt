package site.remlit.orchidcore.command

import com.hypixel.hytale.server.core.command.system.AbstractCommand
import com.hypixel.hytale.server.core.command.system.CommandContext
import site.remlit.orchidcore.exception.GracefulException
import site.remlit.orchidcore.service.TpaService
import site.remlit.orchidcore.util.sendMessage
import java.util.concurrent.CompletableFuture

class TpacceptCommand : AbstractCommand(
    "tpaccept",
    "Accepts a pending teleportation request",
) {
    init {
        this.requirePermission("orchidcore.command.tpaccept")
        this.addAliases("tpac")
    }

    override fun execute(ctx: CommandContext): CompletableFuture<Void> =
        CompletableFuture.runAsync {
            if (!ctx.isPlayer) {
                ctx.sendMessage("Only players can issue this command")
                return@runAsync
            }

            try {
                TpaService.acceptTpa(ctx.sender().uuid)
            } catch (e: GracefulException) {
                ctx.sendMessage(e.message ?: "Command failed")
            }
        }
}