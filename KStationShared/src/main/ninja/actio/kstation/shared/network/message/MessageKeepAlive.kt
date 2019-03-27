package ninja.actio.kstation.shared.network.message

class MessageKeepAlive : Message() {
    override val length: Short = 255
    override val type: Byte = MESSAGE_KEEPALIVE

    override fun deserialize(byteArray: ByteArray) {
        if ((byteArray.size > 1) or (byteArray[0] != 0xFF.toByte()))
            println("malformed keepalive")
            return
    }
}