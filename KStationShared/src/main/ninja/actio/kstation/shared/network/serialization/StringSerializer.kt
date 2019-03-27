package ninja.actio.kstation.shared.network.serialization

import ninja.actio.kstation.shared.util.toArrayByte
import java.nio.ByteBuffer
import java.nio.charset.Charset

object StringSerializer {
    fun serialize(string: String): ByteArray { //converts string to byte array, with a length indicator at the front
        val rawBytes = string.toByteArray(Charset.forName("UTF-8"))
        val length: Short = rawBytes.size.toShort()
        val outArray = ByteArray(rawBytes.size + 2)
        outArray[0] = length.toArrayByte()[1] //Flipped for endianness
        outArray[1] = length.toArrayByte()[0]
        for ((i, byte) in rawBytes.withIndex()) {
            outArray[i + 2] = byte
        }
        return outArray
    }


}

fun ByteBuffer.getString(): String {
    val length = this.short.toInt()
    val initialPosition = this.position()
    val finalPosition = this.position() + length - 1
    val stringByteHolder = ByteArray(length)
    for ((i, offsetIndex) in (initialPosition..finalPosition).withIndex()) {
        stringByteHolder[i] = this[offsetIndex]
    }
    val outString = stringByteHolder.toString(Charset.forName("UTF-8"))
    if (finalPosition <= this.limit())
        this.position(finalPosition + 1)
    return outString
}