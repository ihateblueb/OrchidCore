package site.remlit.blossom.util

import com.hypixel.hytale.server.core.command.system.CommandContext
import site.remlit.blossom.exception.GracefulException
import java.util.concurrent.CompletableFuture

fun runCommand(
    ctx: CommandContext,
    block: () -> Unit
): CompletableFuture<Void> {
    return CompletableFuture.runAsync {
        try {
            block()
        } catch (e: GracefulException) {
            ctx.sendMessage(red { e.message ?: "Command failed" })
        } catch (_: Exception) {}
    }
}