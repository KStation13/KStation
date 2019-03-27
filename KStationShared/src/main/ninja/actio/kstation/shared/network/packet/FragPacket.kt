package ninja.actio.kstation.shared.network.packet

import java.net.InetAddress

class FragPacket(
    address: InetAddress,
    sequence: Int,
    val ack: Int,
    val id: Short,
    val partNumber: Short,
    payload: ByteArray
) :
    Packet(address, payload, PACKET_TYPE_FRAG) {
    override fun serialize() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deserialize() {
        TODO("not implemented")
    }
}