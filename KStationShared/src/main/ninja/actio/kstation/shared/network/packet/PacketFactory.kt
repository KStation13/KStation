package ninja.actio.kstation.shared.network.packet

import java.net.InetAddress
import java.nio.ByteBuffer

object PacketFactory {
    fun deserializePacket(address: InetAddress, byteArray: ByteArray): Packet {
        val packetType = byteArray[0]
        val cutPayload = byteArray.sliceArray(1..byteArray.lastIndex)
        val castPacket = when (packetType) {
            PACKET_TYPE_CONNECT -> ConnectPacket.deserialize(address, cutPayload)
            PACKET_TYPE_UNACKED -> UnackedPacket(address, cutPayload)
            PACKET_TYPE_ACKED -> AckedPacket.deserialize(address, cutPayload)
            //PACKET_TYPE_BIG -> BigPacket(address, sequence, ack, splitId, totalSize, payload)
            //PACKET_TYPE_FRAG -> FragPacket(address, sequence, ack, splitId, partNumber, payload)
            else -> {
                println("Invalid Packet Type")
                return UnackedPacket(address, cutPayload)
            }
        }
        return castPacket
    }
}