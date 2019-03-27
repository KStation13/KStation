package ninja.actio.kstation.client.game.player.tile

import com.badlogic.gdx.graphics.Color
import ninja.actio.kstation.client.game.player.IsoRotation

enum class TileLevel {
    SPACE,
    GIRDER,
    BASE,
    SUBFLOOR,
    FLOOR
}

data class FloorVisual(val texture: String = "", val color: Color = Color.CLEAR, val decals: List<DecalInfo> = listOf())
data class DecalInfo(
    val texture: String = "",
    val rotation: IsoRotation = IsoRotation.SW,
    val color: Color = Color.CLEAR
)

data class subFloorObject(val texture: String)
data class Tile(
    val currentLevel: TileLevel = TileLevel.SPACE,
    val floorVis: FloorVisual = FloorVisual(),
    val subFloor: List<String> = listOf(),
    val supportsBuilt: Boolean = false,
    val baseVis: FloorVisual = FloorVisual(),
    val surface: List<String> = listOf()
)