package ninja.actio.kstation.client.render.iso.tile

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch
import ninja.actio.kstation.client.assetManager
import ninja.actio.kstation.client.game.player.tile.Tile
import ninja.actio.kstation.client.game.player.tile.TileLevel
import ninja.actio.kstation.client.render.iso.IsoWorldRenderer

class IsoFloorRenderer(val isoWorldRenderer: IsoWorldRenderer) {
    //offsets in relation to floor tile
    //even if there isn't a floor tile, it's the reference.
    //Why these numbers?  Why hardcode?
    //eh
    private object Offsets {
        const val girder = -22f
        const val base = -18f
        const val subfloor = -16f
        const val supports = -0.2f
    }

    lateinit var decalBatch: DecalBatch
    lateinit var modelBatch: ModelBatch
    private val drawMap = mutableMapOf<Pair<Int, Int>, Tile>()
    var dirtyDrawList = true

    private fun buildDrawList() {
        drawMap.clear()
        for ((x, yArr) in isoWorldRenderer.world.tiles.withIndex()) {
            for ((y, tile) in yArr.withIndex()) {
                if (tile.currentLevel != TileLevel.SPACE) {
                    drawMap[Pair(x, y)] = tile
                }
            }
        }
    }

    fun create() {
        decalBatch = DecalBatch(CameraGroupStrategy(isoWorldRenderer.cam))
        modelBatch = ModelBatch()
    }


    fun render() {
        if (dirtyDrawList) {
            buildDrawList()
            drawMap
        }

        modelBatch.begin(isoWorldRenderer.cam)
        for (tile in drawMap.toList()) {
            if (openEdgeTowardsCamera(tile) or (tile.second.currentLevel != TileLevel.FLOOR)) {
                renderGirders(tile)
                renderBase(tile)
                renderSupports(tile)
            }

            renderSubLevel(tile)

            if (tile.second.currentLevel != TileLevel.FLOOR) continue
            renderFloor(tile)
            renderSurface(tile)
        }
        modelBatch.end()
    }

    private fun openEdgeTowardsCamera(tile: Pair<Pair<Int, Int>, Tile>): Boolean {
        //TODO: Rotation
        //return ((tile.first.first == 0) or (tile.first.second == 0))
        return true
    }

    private fun renderGirders(tile: Pair<Pair<Int, Int>, Tile>) {
        //drawTileLayer(tile, Offsets.girder)
    }

    private fun drawTileLayer(tile: Pair<Pair<Int, Int>, Tile>, model: String, yOffset: Float = 0f) {
        val gridWidth = 1.5f

        val model = assetManager.get<Model>("$model.g3db")
        val instance = ModelInstance(model)
        instance.transform.setToTranslation(tile.first.first * gridWidth, yOffset, tile.first.second * gridWidth)

        modelBatch.render(instance, isoWorldRenderer.environment)
    }

    private fun renderBase(tile: Pair<Pair<Int, Int>, Tile>) {
        /*drawTileLayer(
            tile,
            Offsets.base
        )*/
        val decalTexes = mutableListOf<Texture>()
        //TODO: Maybe wrap this into a func so it's not repeated twice
        if (tile.second.baseVis.decals.isNotEmpty()) {
            tile.second.baseVis.decals.forEach {
                decalTexes.add(
                    assetManager.get<Texture>(
                        it.texture + "_" + it.rotation.name
                    )
                )
            }
        }
        //TODO: Draw decal texes
    }

    private fun renderSupports(tile: Pair<Pair<Int, Int>, Tile>) {
        if (!tile.second.supportsBuilt) {
            return
        }
        drawTileLayer(tile, "assets/model/tile/support/floorsupports", Offsets.supports)
    }

    private fun renderSubLevel(tile: Pair<Pair<Int, Int>, Tile>) {
        if (tile.second.subFloor.isEmpty()) {
            return
        }
    }

    private fun renderFloor(tile: Pair<Pair<Int, Int>, Tile>) {
        drawTileLayer(tile, "assets/model/tile/floor/floor")

        val decalTexes = mutableListOf<Texture>()
        if (tile.second.floorVis.decals.isNotEmpty()) {
            tile.second.floorVis.decals.forEach {
                decalTexes.add(
                    assetManager.get<Texture>(
                        it.texture + "_" + it.rotation.name
                    )
                )
            }
        }
    }

    private fun renderSurface(tile: Pair<Pair<Int, Int>, Tile>) {
        if (tile.second.surface.isEmpty()) {
            return
        }
    }


    fun dispose() {
        modelBatch.dispose()
        decalBatch.dispose()
    }
}
