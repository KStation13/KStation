package ninja.actio.kstation.shared.network.message

const val MESSAGE_KEEPALIVE:Byte = 1
const val MESSAGE_CONNECT:Byte = 2
const val MESSAGE_CHALLENGE:Byte = 3
const val MESSAGE_CHALLENGERESPONSE:Byte = 4
const val MESSAGE_DISCONNECT:Byte = 5


val networkMessages = listOf(
    MESSAGE_KEEPALIVE,
    MESSAGE_CONNECT,
    MESSAGE_CHALLENGE,
    MESSAGE_CHALLENGERESPONSE,
    MESSAGE_DISCONNECT
)