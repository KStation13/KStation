package ninja.actio.kstation.client.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Stack
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.VisLabel
import com.kotcrab.vis.ui.widget.VisProgressBar
import ktx.graphics.use
import ktx.vis.stack
import ninja.actio.kstation.client.assetManager
import ninja.actio.kstation.client.game

class LoadingScreen : KStationScreen() {
    lateinit var progressBar: VisProgressBar
    lateinit var label: VisLabel
    lateinit var background: Texture
    lateinit var spriteBatch: SpriteBatch
    lateinit var root: Stack

    var progress = 0f

    override fun show() {
        VisUI.load()
        root = stack {
            originX = 50f
            x = (Gdx.graphics.width / 2f) - 75f
            y = 100f
            progressBar = progressBar()
        }

        background = Texture("assets/texture/background/NONAGONINFINITY2.png")
        spriteBatch = SpriteBatch()
    }

    override fun render(delta: Float) {
        if (assetManager.update()) {
            game.setScreen(GameScreen::class.java)
        }
        progressBar.value = assetManager.progress
        spriteBatch.use {
            spriteBatch.draw(background, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            root.draw(spriteBatch, 1f)
        }
    }

    override fun hide() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun dispose() {
        VisUI.dispose()
    }
}