package ninja.actio.kstation.shared.network.message

import java.io.ByteArrayOutputStream


abstract class Message {
    abstract val type: Byte
    abstract val length: Short
    val payloadBuilder = ByteArrayOutputStream()

    fun serializeBasicInfo() {
        payloadBuilder.write(byteArrayOf(type))
    }

    open fun serialize(): ByteArray {
        serializeBasicInfo()
        return payloadBuilder.toByteArray()
    }

    abstract fun deserialize(byteArray: ByteArray)
}