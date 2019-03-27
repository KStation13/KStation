package ninja.actio.kstation.client.render.iso.debug

import com.badlogic.gdx.graphics.Color
import ninja.actio.kstation.client.render.iso.IsoWorld
import ninja.actio.kstation.client.game.player.tile.FloorVisual
import ninja.actio.kstation.client.game.player.tile.Tile
import ninja.actio.kstation.client.game.player.tile.TileLevel

fun getTestWorld(): IsoWorld {
    val outWorld = IsoWorld()

    for (x in (0..4)) {
        for (y in (0..4)) {
            outWorld.tiles[x][y] = defaultTestTile()
        }
    }


    return outWorld
}

fun defaultTestTile(): Tile {
    return Tile(
        TileLevel.FLOOR,
        FloorVisual(
            "assets/texture/tile/floor/test_ground",
            Color.CLEAR,
            listOf()
        ),
        listOf(),
        true,
        FloorVisual(
            "assets/texture/tile/base/base_test",
            Color.CLEAR,
            listOf()
        ),
        listOf()
    )
}

