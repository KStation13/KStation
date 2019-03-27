package ninja.actio.kstation.shared.network

import ninja.actio.kstation.shared.network.message.Message
import java.net.Inet4Address
import java.net.InetAddress

data class ConnectionSlot(
    val salt: Long = 0L,
    var localSeqNumber: Int = 0,
    var remoteSeqNumber: Int = 0,
    val outgoingMessageQueue: MutableList<Message> = mutableListOf(),
    val incomingMessageQueue: MutableList<Message> = mutableListOf()
)