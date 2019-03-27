package ninja.actio.kstation.server.network

import com.uchuhimo.collections.mutableBiMapOf
import ninja.actio.kstation.shared.network.ConnectionSlot
import ninja.actio.kstation.shared.network.Network
import ninja.actio.kstation.shared.network.message.Message
import ninja.actio.kstation.shared.network.packet.CONNECT_PACKET_PAD_LENGTH
import ninja.actio.kstation.shared.network.packet.ConnectPacket
import ninja.actio.kstation.shared.network.packet.PACKET_TYPE_CONNECT
import ninja.actio.kstation.shared.network.packet.PacketFactory
import java.net.DatagramSocket
import java.net.InetAddress

class Server(val serverConfig: ServerConfig = ServerConfig()) : Network() {
    val connectedMap = mutableBiMapOf<InetAddress, String>()
    val connectionSlotMap = mutableMapOf<InetAddress, ConnectionSlot>()

    override var receiveSocket = DatagramSocket(serverConfig.port)

    override fun sendOutgoingPackets() {
        // Each target has it's own send queue
        // This is probably hilariously inefficient, but whatever
        // I'll find out if it is during testing
        //for ((target, info) in connectedClients) {
        //val outPacket = buildPacket(info)
        //}
    }

    /*
    override fun deserializePacket(message: DatagramPacket) {

    }
    */

    override fun handleIncomingPacket() {
        if (packetBuffer.data[0] == PACKET_TYPE_CONNECT)
            if (packetBuffer.length != CONNECT_PACKET_PAD_LENGTH) {
                //Discard malformed connect packets
                println("Malformed connect packet received")//TODO: Remove this after testing for less logspam
                return
            } else {
                connect(packetBuffer.address)
                return
            }
        else if (packetBuffer.address !in connectedMap.keys) {
            //Silently discard non-connect packets from non-connected clients
            return
        }
        super.handleIncomingPacket()
    }


    override fun connect(ip: InetAddress) {
        val deserializedConnect = PacketFactory.deserializePacket(ip, packetBuffer.data) as ConnectPacket
        connectedMap[ip] = deserializedConnect.clientInfo.username
        connectionSlotMap[ip] = ConnectionSlot(deserializedConnect.clientInfo.salt)

    }

    override fun disconnect(ip: InetAddress) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun queuePacket(address: InetAddress, message: Message) {
        if (address in connectedMap.keys)
            connectionSlotMap[address]!!.outgoingMessageQueue.add(message)
        else
            println("Just tried to send a packet to a disconnected client")
    }

    fun queuePacket(username: String, message: Message) {
        val inverse = connectedMap.inverse
        if (username in inverse)
            queuePacket(inverse[username]!!, message)
        else
            println("Just tried to send a packet to a disconnected client")
    }

}