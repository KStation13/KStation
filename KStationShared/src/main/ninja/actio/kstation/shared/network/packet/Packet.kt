package ninja.actio.kstation.shared.network.packet

import ninja.actio.kstation.shared.network.message.Message
import ninja.actio.kstation.shared.network.serialization.MessageFactory
import ninja.actio.kstation.shared.util.toInt
import ninja.actio.kstation.shared.util.toShort
import java.io.ByteArrayOutputStream
import java.net.DatagramPacket
import java.net.InetAddress


const val PACKET_MAX_LENGTH = 1400
const val PACKET_SEQUACK_LENGTH = 64
const val PACKET_MESSAGE_PAYLOAD_LENGTH = PACKET_MAX_LENGTH - PACKET_SEQUACK_LENGTH

const val PACKET_TYPE_CONNECT: Byte = 0
const val PACKET_TYPE_UNACKED: Byte = 1
const val PACKET_TYPE_ACKED: Byte = 2
const val PACKET_TYPE_BIG: Byte = 3
const val PACKET_TYPE_FRAG: Byte = 4

abstract class Packet(val address: InetAddress, val messagePayload: ByteArray) {
    abstract val type: Byte

    abstract fun serialize(): ByteArray

    fun generateDatagramPacket(port: Int): DatagramPacket {
        val serialized = serialize()
        return DatagramPacket(serialized, serialized.size, address, port)
    }
}