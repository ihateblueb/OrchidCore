package site.remlit.blossom.command

import com.hypixel.hytale.server.core.command.system.AbstractCommand
import com.hypixel.hytale.server.core.command.system.CommandContext
import site.remlit.blossom.exception.GracefulException
import site.remlit.blossom.service.TpaService
import site.remlit.blossom.util.runCommand
import java.util.concurrent.CompletableFuture

class TpdenyCommand : AbstractCommand(
    "tpdeny",
    "Denies a pending teleportation request",
) {
    init {
        this.requirePermission("blossom.command.tpdeny")
        this.addAliases("tpreject", "tpd", "tpr")
    }

    override fun execute(ctx: CommandContext): CompletableFuture<Void> =
        runCommand(ctx) {
            if (!ctx.isPlayer)
                throw GracefulException("Only players can issue this command")

            TpaService.denyTpa(ctx.sender().uuid)
        }
}