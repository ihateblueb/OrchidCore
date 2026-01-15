package site.remlit.blossom.formatter

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.universe.PlayerRef

typealias Placeholders = Map<String, String>

object Formatter {
    /**
     * Generate placeholders for players. Prefixed with `player`.
     *
     * @param playerRef PlayerRef object
     *
     * @return Generated placeholders
     * */
    fun playerPlaceholders(playerRef: PlayerRef): Placeholders {
        return mapOf(
            "playerUuid" to playerRef.uuid.toString(),
            "playerWorldUuid" to playerRef.worldUuid.toString(),
            "playerUsername" to playerRef.username,
            "playerPositionX" to playerRef.transform.position.x.toString(),
            "playerPositionY" to playerRef.transform.position.y.toString(),
            "playerPositionZ" to playerRef.transform.position.z.toString()
        )
    }

    /**
     * Merge multiple placeholder maps into one.
     *
     * @param placeholders Placeholder maps
     *
     * @return Merged placeholder map
     * */
    @JvmStatic
    fun mergePlaceholders(
        vararg placeholders: Placeholders
    ): Map<String, String> {
        val map = mutableMapOf<String, String>()

        placeholders.forEach { set ->
            set.forEach { (k, v) -> map[k] = v }
        }

        return map.toMap()
    }

    /**
     * Format a message from string
     *
     * @param placeholders Placeholders
     * @param string String to format
     * @param colors Whether to use colors
     *
     * @return Formatted message
     * */
    @JvmStatic
    fun format(
        placeholders: Placeholders,
        string: String,
        colors: Boolean = true
    ): Message {
        var string = string
        val placeholders = mergePlaceholders(
            placeholders,
            FormatterConfiguration.resolveGlobalPlaceholders()
        )

        placeholders.forEach { (k, v) ->
            string = string.replace("%$k%", v)
        }

        if (!colors) return Message.raw(string)
        return Message.raw(string)

        //val colorStart = "/<[#a-zA-Z0-9]*?>/".toRegex()
        //val colorEnd = "/</[#a-zA-Z0-9]*?>/".toRegex()
    }

    /**
     * Format a message from string
     *
     * @param string String to format
     * @param colors Whether to use colors
     *
     * @return Formatted message
     * */
    @JvmStatic
    fun format(string: String, colors: Boolean = true) = format(
        emptyMap(),
        string,
        colors
    )

    /**
     * Format a message from string
     *
     * @param placeholders Placeholders
     * @param string String to format
     *
     * @return Formatted message, as string
     * */
    @JvmStatic
    fun formatToString(
        placeholders: Placeholders,
        string: String
    ): String {
        var string = string

        placeholders.forEach { (k, v) ->
            string = string.replace("%$k%", v)
        }

        return string
    }

    /**
     * Format a message from string
     *
     * @param string String to format
     *
     * @return Formatted message, as string
     * */
    @JvmStatic
    fun formatToString(string: String) = format(
        emptyMap(),
        string
    )
}