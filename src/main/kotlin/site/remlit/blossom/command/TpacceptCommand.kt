package site.remlit.blossom.command

import com.hypixel.hytale.server.core.command.system.AbstractCommand
import com.hypixel.hytale.server.core.command.system.CommandContext
import site.remlit.blossom.exception.GracefulException
import site.remlit.blossom.service.TpaService
import site.remlit.blossom.util.runCommand
import java.util.concurrent.CompletableFuture

class TpacceptCommand : AbstractCommand(
    "tpaccept",
    "Accepts a pending teleportation request",
) {
    init {
        this.requirePermission("blossom.command.tpaccept")
        this.addAliases("tpac")
    }

    override fun execute(ctx: CommandContext): CompletableFuture<Void> =
        runCommand(ctx) {
            if (!ctx.isPlayer)
                throw GracefulException("Only players can issue this command")

            TpaService.acceptTpa(ctx.sender().uuid)
        }
}