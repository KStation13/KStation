package ninja.actio.kstation.shared.network.packet

import java.net.InetAddress
import java.nio.ByteBuffer

class UnackedPacket(address: InetAddress, messagePayload: ByteArray) :
    Packet(address, messagePayload) {
    override val type = PACKET_TYPE_UNACKED

    //No deserialize because it's unnecessary, see PacketFactory

    override fun serialize(): ByteArray {
        val outArray = ByteArray(messagePayload.size + 1)
        outArray[0] = type
        messagePayload.copyInto(outArray, 1)
        return outArray
    }
}