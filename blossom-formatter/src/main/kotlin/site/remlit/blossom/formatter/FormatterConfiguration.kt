package site.remlit.blossom.formatter

/**
 * Configuration holder for Blossom's formatter
 * */
object FormatterConfiguration {
    /**
     * Supported predefined colors. You can add to this, if needed.
     * */
    @JvmStatic
    val colors = mutableMapOf(
        "red" to "#ff5d52",
        "orange" to "#ffb026",
        "yellow" to "#ffdf52",
        "green" to "#34d944",
        "blue" to "#7878ff",
        "purple" to "#8b61ff",
        "pink" to "#f872fc",
        "gray" to "#808080",
        "darkGray" to "#474747"
    )

    /**
     * Global placeholders that will work anywhere. You can add to this, if needed.
     * */
    @JvmStatic
    val globalPlaceholders = mutableMapOf<String, () -> String>()

    @JvmStatic
    fun resolveGlobalPlaceholders(): Placeholders {
        val map = mutableMapOf<String, String>()

        globalPlaceholders.forEach { (key, function) ->
            map[key] = function()
        }

        return map
    }
}