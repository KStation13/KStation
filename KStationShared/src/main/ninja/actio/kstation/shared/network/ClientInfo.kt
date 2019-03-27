package ninja.actio.kstation.shared.network

data class ClientInfo(
    val username: String = "",
    val salt: Long = 0L,
    val version: String = "",
    val contentHash: String = ""
)