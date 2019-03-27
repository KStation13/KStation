package ninja.actio.kstation.client

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

val game = KStationClient()

fun main(args: Array<String>) {
    val config = LwjglApplicationConfiguration()
    config.title = "KStation"
    config.width = 1280
    config.height = 720
    LwjglApplication(game, config)
}