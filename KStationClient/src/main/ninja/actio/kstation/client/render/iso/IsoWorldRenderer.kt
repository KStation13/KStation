package ninja.actio.kstation.client.render.iso

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.VertexAttributes
import com.badlogic.gdx.graphics.g3d.*
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import ninja.actio.kstation.client.render.iso.tile.IsoFloorRenderer

object MagicIsoNumbers {

}

enum class ZoomLevel {
    MAX,
    MID,
    MIN
}

enum class IsoRotation {

}

class IsoWorldRenderer(var world: IsoWorld = IsoWorld()) {
    private val camXLocation = 7.0710678118654755
    private val camYLocation = 5.77350269189626
    private val camZLocation = 7.0710678118654755

    var cam = OrthographicCamera()
    var model = Model()
    lateinit var instance: ModelInstance
    var batch = ModelBatch()
    var environment = Environment()
    val floorRenderer = IsoFloorRenderer(this)


    fun create() {
        environment.set(ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f))
        environment.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f))
        cam = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        cam.apply {
            position.set(camXLocation.toFloat(), camYLocation.toFloat(), camZLocation.toFloat())
            zoom = 0.01f
            lookAt(0f, 0f, 0f)
            println(direction)
            far = 300f
            update()
        }
        val builder = ModelBuilder()
        model = builder.createBox(5f, 5f, 5f,
            Material(ColorAttribute.createDiffuse(Color.GREEN)),
            (VertexAttributes.Usage.Position or VertexAttributes.Usage.Normal).toLong()
        )
        instance = ModelInstance(model)
        floorRenderer.create()
    }

    fun render() {
        /*
        decalBatch.begin(cam)
        decalBatch.render(instance, environment)
        decalBatch.end()
        */
        floorRenderer.render()
    }

    fun dispose() {
        model.dispose()
        batch.dispose()
        floorRenderer.dispose()
    }
}