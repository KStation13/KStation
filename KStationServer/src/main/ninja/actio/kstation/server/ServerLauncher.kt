package ninja.actio.kstation.server

import ninja.actio.kstation.server.KStationServer
import ninja.actio.kstation.server.network.ServerConfig

fun main(args: Array<String>) {
    val server = KStationServer(ServerConfig())
    server.initialize()
}
