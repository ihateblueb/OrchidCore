package site.remlit.blossom.model

import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    var version: Int = 0,
    var chat: ChatConfiguration = ChatConfiguration(),
    var tpask: TpaskConfiguration = TpaskConfiguration(),
    var discord: DiscordConfiguration = DiscordConfiguration(),
)

@Serializable
data class ChatConfiguration(
    var enabled: Boolean = true,
    var format: String = "%username%: %msg%",
)

@Serializable
data class TpaskConfiguration(
    var expireTime: Int = 30,
    var teleportWait: Int = 3,
)

@Serializable
data class DiscordConfiguration(
    var enabled: Boolean = false,
    var token: String = "CHANGEME",
    var channelId: String = "CHANGEME",

    var serverStartFormat: String = ":white_check_mark: **Server started**",
    var serverStopFormat: String = ":stop_sign: **Server stopped**",

    var joinFormat: String = ":arrow_right: **%username% joined the server**",
    var leaveFormat: String = ":arrow_left: **%username% left the server**",

    var hytaleToDiscordFormat: String = "**%username%:** %msg%",
    var discordToHytaleFormat: String = "(Discord) %username%: %msg%",
)
