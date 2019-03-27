package ninja.actio.kstation.shared.network.serialization

import ninja.actio.kstation.shared.network.message.*
import ninja.actio.kstation.shared.network.message.InvalidMessageTypeException
import ninja.actio.kstation.shared.util.toShort

object MessageFactory {
    fun deserialize(byteArray: ByteArray): Message {
        val type = byteArray[0]
            return when (type) {
                MESSAGE_KEEPALIVE -> MessageKeepAlive()
                MESSAGE_CONNECT -> MessageConnect()
                else -> {
                    throw InvalidMessageTypeException()
                }
            }
    }

    fun deserializeMessages(byteArray: ByteArray): List<Message> {
        val deserialized = mutableListOf<Message>()
        var startOfMessage = 0
        var index = 0
        while (index < byteArray.lastIndex) {
            index++
            val length = byteArray.sliceArray(index..(index + 1)).toShort()
            index += 2
            index += length
            val message = MessageFactory.deserialize(
                byteArray.sliceArray(startOfMessage..index)
            )
            deserialized.add(message)
            startOfMessage = ++index
        }
        return listOf()
    }
}