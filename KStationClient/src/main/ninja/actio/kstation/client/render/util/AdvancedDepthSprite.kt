package ninja.actio.kstation.client.render.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

class AdvancedDepthSprite(
    tex: Texture,
    val depthTex: Texture,
    srcX: Int = 0,
    srcY: Int = 0,
    srcWidth: Int = tex.width,
    srcHeight: Int = tex.height
) : Sprite(tex, srcX, srcY, srcWidth, srcHeight)
