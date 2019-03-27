package ninja.actio.kstation.shared.network.serialization

import java.nio.ByteBuffer

object LongSerializer {
    fun longToBytes(l: Long): ByteArray {
        var l = l
        val result = ByteArray(8)
        for (i in 7 downTo 0) {
            result[i] = (l and 0xFF).toByte()
            l = l shr 8
        }
        return result
    }

    fun bytesToLong(b: ByteArray): Long {
        var result: Long = 0
        for (i in 0..7) {
            result = result shl 8
            result = (result or (b[i].toLong() and 255))
        }
        return result
    }
}
