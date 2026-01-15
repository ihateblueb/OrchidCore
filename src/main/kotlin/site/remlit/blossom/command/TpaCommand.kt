package site.remlit.blossom.command

import com.hypixel.hytale.server.core.command.system.AbstractCommand
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes
import com.hypixel.hytale.server.core.universe.PlayerRef
import site.remlit.blossom.exception.GracefulException
import site.remlit.blossom.service.TpaService
import site.remlit.blossom.util.runCommand
import java.util.concurrent.CompletableFuture

class TpaCommand : AbstractCommand(
    "tpask",
    "Asks player if you can teleport to them"
) {
    val targetPlayerArg: RequiredArg<PlayerRef> = withRequiredArg<PlayerRef>(
        "targetPlayer",
        "Player to request to teleport to",
        ArgTypes.PLAYER_REF
    )

    init {
        this.requirePermission("blossom.command.tpask")
        this.addAliases("tpa")
    }

    override fun execute(ctx: CommandContext): CompletableFuture<Void> =
        runCommand(ctx) {
            if (!ctx.isPlayer)
                throw GracefulException("Only players can issue this command")

            val target = targetPlayerArg.get(ctx)

            TpaService.appendTpa(ctx.sender().uuid, target.uuid)
        }
}