package ninja.actio.kstation.shared.network.packet

import java.net.InetAddress

class BigPacket(
    address: InetAddress,
    sequence: Int,
    ack: Int,
    val id: Short,
    val totalSize: Short,
    messagePayload: ByteArray) :
    AckedPacket(address, sequence, ack, messagePayload) {
    override fun serialize(): ByteArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
