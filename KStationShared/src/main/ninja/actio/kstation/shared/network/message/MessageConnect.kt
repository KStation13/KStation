package ninja.actio.kstation.shared.network.message

import ninja.actio.kstation.shared.network.ClientInfo

class MessageConnect(var clientInfo: ClientInfo = ClientInfo()) : Message() {
    override val type = MESSAGE_CONNECT
    override val length: Short
        get() {
            return 1024
        }

    override fun serialize(): ByteArray {
        serializeBasicInfo()

        val serializedUsername = clientInfo.username.toByteArray()
        val serializedVersion = clientInfo.version.toByteArray()
        if (serializedUsername.size > 255) throw BigPayloadException()
        payloadBuilder.write(byteArrayOf(serializedUsername.size.toByte()))
        payloadBuilder.write(clientInfo.username.toByteArray())
        return payloadBuilder.toByteArray()
    }

    override fun deserialize(byteArray: ByteArray) {

    }

}