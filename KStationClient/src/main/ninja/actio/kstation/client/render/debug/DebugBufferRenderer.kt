package ninja.actio.kstation.client.render.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class DebugBufferRenderer {
    lateinit var tex: Texture
    val batch = SpriteBatch()

    fun init() {
        tex = Texture(Gdx.graphics.width, Gdx.graphics.height, Pixmap.Format.LuminanceAlpha)
    }

    abstract fun copyBufferToTex()

    fun render() {
        copyBufferToTex()
        batch.begin()
        batch.draw(tex, 0f, 0f)
        batch.end()
    }
}