package ninja.actio.kstation.server.network

data class ServerConfig(
    val port: Int = 38195,
    val serverUpdateRate: Int = 20, //Packets per second
    val clientUpdateRate: Int = 10
)
