package ninja.actio.kstation.client.screens

import ninja.actio.kstation.client.game
import ninja.actio.kstation.client.preloadAllBaseAssets

class PreInitScreen : KStationScreen() {
    override fun show() {
        game.addScreen(GameScreen())
        game.addScreen(LoadingScreen())
        preloadAllBaseAssets()
        game.setScreen(LoadingScreen::class.java)
    }
}