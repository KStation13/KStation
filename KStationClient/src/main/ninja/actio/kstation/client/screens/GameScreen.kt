package ninja.actio.kstation.client.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import ninja.actio.kstation.client.assetManager
import ninja.actio.kstation.client.game
import ninja.actio.kstation.client.render.iso.IsoWorldRenderer

class GameScreen : KStationScreen() {
    lateinit var depth: Texture
    lateinit var input: InputProcessor
    var worldRenderer = IsoWorldRenderer()

    override fun show() {
        game.initNetwork()

        /*
        DebugRenders.init()
        worldRenderer.create()
        worldRenderer.world = getTestWorld()
        Gdx.input.inputProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                when (keycode) {
                    Input.Keys.W -> worldRenderer.cam.translate(0f, 50f)
                    Input.Keys.A -> worldRenderer.cam.translate(-50f, 0f)
                    Input.Keys.S -> worldRenderer.cam.translate(0f, -50f)
                    Input.Keys.D -> worldRenderer.cam.translate(50f, 0f)
                }
                return true
            }
        }
        */
    }

    override fun render(delta: Float) {
        if (assetManager.update()) {

        }

        Gdx.gl.glClearColor(0.6015f, 0.3906f, 0.3906f, 1f)
        //Gdx.gl.glClearDepthf(1f)
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT)

        //Gdx.gl.glEnable(GL20.GL_DEPTH_TEST)

        //Gdx.gl.glDepthMask(true)

        //worldRenderer.render()
    }

    override fun hide() {
        worldRenderer.dispose()
    }
}
