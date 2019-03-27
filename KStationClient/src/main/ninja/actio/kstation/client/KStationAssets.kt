package ninja.actio.kstation.client

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.Model
import java.io.File

val assetManager = AssetManager()

fun preloadAllBaseAssets() {
    Gdx.app.debug("AssetManager", "Beginning preload...")
    val files = File("assets").walk().forEach {
        if (it.isFile) {
            Gdx.app.debug("AssetManager", "Queuing file to load: ${it.path}")
            when (it.extension) {
                "png", "jpg" -> assetManager.load(it.path, Texture::class.java)
                "g3db" -> assetManager.load(it.path, Model::class.java)
                else -> Gdx.app.log("AssetManager", "WARNING: Nonasset file detected in assets dir.  ${it.path}")
            }
        }
    }
}


