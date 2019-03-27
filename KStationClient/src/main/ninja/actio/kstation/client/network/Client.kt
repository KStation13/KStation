package ninja.actio.kstation.client.network

import ninja.actio.kstation.client.settings.GlobalSettings
import ninja.actio.kstation.shared.network.ClientInfo
import ninja.actio.kstation.shared.network.ConnectionSlot
import ninja.actio.kstation.shared.network.Network
import ninja.actio.kstation.shared.network.packet.ConnectPacket
import ninja.actio.kstation.shared.network.packet.PACKET_MAX_LENGTH
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class Client(val port: Int) : Network() {
    var connectedServerAddress = InetAddress.getByName("localhost")
    var connectedServerInfo = ConnectionSlot()
    override var receiveSocket = DatagramSocket(port)

    override fun connect(target: InetAddress) {
        println("Connecting to $target...")
        val clientInfo = ClientInfo("actioninja", salt, "test", "test")
        val connectPacket = ConnectPacket(target, clientInfo)
        connectPacket.serialize()
        val datagram = connectPacket.generateDatagramPacket(port)
        sendSocket.send(datagram)
        val incomingPacket = DatagramPacket(ByteArray(PACKET_MAX_LENGTH), PACKET_MAX_LENGTH)
        println("Awaiting challenge...")
    }

    fun challengeResponse() {
        println("Responding to challenge...")
    }

    override fun disconnect(target: InetAddress) {
    }

    fun buildClientInfo(): ClientInfo {
        return ClientInfo(
            GlobalSettings.username,
            salt,
            "test", //TODO: version and contentHash implementation
            "test"
        )
    }

    override fun sendOutgoingPackets() {
        val packet = DatagramPacket(ByteArray(10) { 127 }, 10, InetAddress.getByName("127.0.0.1"), 38195)
        receiveSocket.send(packet)
        println("Sending packet")
    }
}
