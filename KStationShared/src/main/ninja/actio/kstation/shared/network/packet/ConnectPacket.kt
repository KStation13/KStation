package ninja.actio.kstation.shared.network.packet

import ninja.actio.kstation.shared.network.ClientInfo
import ninja.actio.kstation.shared.network.serialization.LongSerializer
import ninja.actio.kstation.shared.network.serialization.StringSerializer
import ninja.actio.kstation.shared.network.serialization.getString
import java.net.InetAddress
import java.nio.ByteBuffer

const val CONNECT_PACKET_PAD_LENGTH = 1000

class ConnectPacket(address: InetAddress, val clientInfo: ClientInfo) :
    Packet(address, ByteArray(0)) {
    companion object {
        fun deserialize(address: InetAddress, byteArray: ByteArray): ConnectPacket {
            val buffer = ByteBuffer.wrap(byteArray)
            val username = buffer.getString()
            val salt = buffer.long
            val version = buffer.getString()
            val contentHash = buffer.getString()
            return ConnectPacket(address, ClientInfo(username, salt, version, contentHash))
        }
    }

    override val type = PACKET_TYPE_CONNECT

    override fun serialize(): ByteArray {
        val buffer = ByteBuffer.allocate(1000)
        buffer.put(type)
        buffer.put(StringSerializer.serialize(clientInfo.username))
        buffer.put(LongSerializer.longToBytes(clientInfo.salt))
        buffer.put(StringSerializer.serialize(clientInfo.version))
        buffer.put(StringSerializer.serialize(clientInfo.contentHash))
        val filledArray = ByteArray(CONNECT_PACKET_PAD_LENGTH)
        buffer.array().copyInto(filledArray)
        return filledArray
    }
}