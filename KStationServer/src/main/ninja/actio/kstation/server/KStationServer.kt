package ninja.actio.kstation.server

import ninja.actio.kstation.server.network.Server
import ninja.actio.kstation.server.network.ServerConfig
import ninja.actio.kstation.shared.network.DEFAULT_PORT

const val TICKS_PER_SECOND = 30
const val TICK_INTERVAL = 1000 / TICKS_PER_SECOND
const val MAX_FRAMESKIP = 5

class KStationServer(serverConfig: ServerConfig) {
    val networkServer = Server(serverConfig)

    var running = false
    var loops = 0
    var delta = 0f
    var nextTick = System.currentTimeMillis()
    var tickNumber = 0 //32 bit: If you attempt to run the networkServer for ~136 years straight at a 30 tickrate, it will crash

    fun initialize() {
        println("Initializing networkServer...")
        running = true
        networkServer.initialize(DEFAULT_PORT) //Networking Server runs on it's own thread
        mainLoop()
    }

    fun mainLoop() {
        while (running) {
            loops = 0
            while ((System.currentTimeMillis() > nextTick) and (loops < MAX_FRAMESKIP)) {
                gameTick()
                nextTick += TICK_INTERVAL
                loops++
            }

            delta = (System.currentTimeMillis() + TICK_INTERVAL - nextTick).toFloat() / TICK_INTERVAL.toFloat()

            rapidTick(delta)
        }
    }

    fun gameTick() {
        tickNumber++
    }

    fun rapidTick(delta: Float) {

    }
}