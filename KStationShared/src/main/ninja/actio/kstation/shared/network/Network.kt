package ninja.actio.kstation.shared.network

import ninja.actio.kstation.shared.network.message.InvalidMessageTypeException
import ninja.actio.kstation.shared.network.message.Message
import ninja.actio.kstation.shared.network.message.networkMessages
import ninja.actio.kstation.shared.network.packet.*
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import kotlin.concurrent.thread
import kotlin.random.Random

const val DEFAULT_PORT = 38195

abstract class Network {
    var sendRate = 30
    val sendInterval: Int
        get() = (1000 / sendRate)
    var nextSend = System.currentTimeMillis()

    abstract var receiveSocket: DatagramSocket //Separate send and receive sockets for async sending and receiving
    val sendSocket = DatagramSocket()
    var salt: Long = 0
    val receivedPackets = mutableListOf<Packet>()
    var state = NetworkState.STOPPED
    var packetBuffer = DatagramPacket(ByteArray(PACKET_MAX_LENGTH), PACKET_MAX_LENGTH)


    val networkReceiveThread = thread(start = false, isDaemon = true) {
        while (state == NetworkState.LIVE) {
            println("Awaiting Packet")
            handleIncomingPacket()
        }
    }

    val networkSendThread = thread(start = false, isDaemon = true) {
        while (state == NetworkState.LIVE) {
            if (System.currentTimeMillis() > nextSend) {
                sendOutgoingPackets()
                nextSend += sendInterval
           }
        }
    }

    open fun handleIncomingPacket() {
        receiveSocket.receive(packetBuffer)
        //val deserializedPacket = PacketFactory.deserializePacket(packetBuffer.address, packetBuffer.data)

    }

    fun handleNetworkMessage(message: Message) {
        if (!networkMessages.contains(message.type))
            throw InvalidMessageTypeException()
    }

    fun deserializePacket(packet: DatagramPacket, target: ConnectionSlot) {
        //val deserialized = PacketFactory.deserializePacket(packet.data)
    }


    open fun initialize(port: Int) {
        println("Initializing network...")
        salt = Random.nextLong()
        state = NetworkState.READY
    }


    fun transistionToLive(port: Int) {
        println("Transitioning to a live state...")
        state = NetworkState.LIVE
        networkReceiveThread.start()
        networkSendThread.start()
        println("Server Ready.")
    }

    fun shutdown() {
        state = NetworkState.STOPPED
        receiveSocket.close()
    }

    abstract fun sendOutgoingPackets()

    /*
    fun buildPacket(info: ConnectionSlot): Packet {
        var sizeBuffer = 0
        var numberOfMessages = 0
        // First we have to decide what goes in a message
        // Packets can consist of multiple messages
        // So we add the length of messages until it's longer than the max length
        while (sizeBuffer < PACKET_MESSAGE_PAYLOAD_LENGTH) {
            sizeBuffer += 3 //Size of the type and length bytes
            sizeBuffer += info.outgoingMessageQueue[numberOfMessages].length.toInt()
            numberOfMessages++
        }
        numberOfMessages -= 2 //Then we subtract 1 because the last added message would put us over the limit
        // We're also going to iterate from 0, so we subtract 1 again, hence 2
        val messageForPacket = mutableListOf<Message>()
        for(packetNumber in (0..sizeBuffer)) {
            messageForPacket.add(info.outgoingMessageQueue[packetNumber])
        }
        return AckedPacket(0, 0, byteArrayOf(), In)
    }
    */


    abstract fun connect(ip: InetAddress)
    abstract fun disconnect(ip: InetAddress)
}

