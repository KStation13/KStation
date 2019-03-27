package ninja.actio.kstation.shared.network.packet

import java.net.InetAddress
import java.nio.ByteBuffer

open class AckedPacket(address: InetAddress, val sequence: Int, val ack: Int, messagePayload: ByteArray) : Packet(address, messagePayload)
{
    companion object {
        fun deserialize(address: InetAddress, array: ByteArray): AckedPacket {
            val buffer = ByteBuffer.wrap(array)
            val type = buffer.get()
            val sequence = buffer.int
            val ack = buffer.int
            val messagePayload = array.sliceArray((buffer.position() - 1)..array.lastIndex)
            return AckedPacket(address, sequence, ack, messagePayload)
        }
    }

    override val type = PACKET_TYPE_ACKED

    override fun serialize(): ByteArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}