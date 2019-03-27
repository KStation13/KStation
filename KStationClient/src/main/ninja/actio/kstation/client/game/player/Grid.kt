package ninja.actio.kstation.client.game.player

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import ninja.actio.kstation.client.game.player.tile.Tile


class Grid {
    var location = Vector2(0f, 0f)
    var rotation = Vector3(0f, 0f, 0f)
    val tiles = mutableMapOf<Pair<Int, Int>, Tile>()
    //val walls = mutableMapOf<Pair<Int, Int>, Wall>()
}