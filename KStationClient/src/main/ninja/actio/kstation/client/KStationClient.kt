package ninja.actio.kstation.client

import ktx.app.KtxGame
import ninja.actio.kstation.client.network.Client
import ninja.actio.kstation.client.screens.KStationScreen
import ninja.actio.kstation.client.screens.PreInitScreen
import ninja.actio.kstation.shared.network.DEFAULT_PORT

class KStationClient : KtxGame<KStationScreen>(PreInitScreen()) {
    val networkClient = Client(38194)

    init {
    }

    fun initNetwork() {
        networkClient.initialize(DEFAULT_PORT - 1)
    }

    fun gameTick() {

    }
}
