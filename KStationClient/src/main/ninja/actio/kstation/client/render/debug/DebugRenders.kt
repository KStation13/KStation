package ninja.actio.kstation.client.render.debug

enum class RenderMode {
    NORMAL,
    DEPTH
}

object DebugRenders {

    var renderMode = RenderMode.NORMAL

    val depthRenderer = DebugBufferRendererDepth()
    fun init() {
        depthRenderer.init()
    }

    fun render() {
        when (renderMode) {
            RenderMode.NORMAL -> return
            RenderMode.DEPTH -> {
                depthRenderer.render()
            }
        }
    }
}