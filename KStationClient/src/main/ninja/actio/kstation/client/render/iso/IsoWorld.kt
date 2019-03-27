package ninja.actio.kstation.client.render.iso

import ninja.actio.kstation.client.game.player.tile.Tile

class IsoWorld {
    var tiles = Array(256) { _ ->
        Array(256) { _ -> Tile() }
    }
}