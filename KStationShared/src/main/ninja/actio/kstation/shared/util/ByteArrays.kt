package ninja.actio.kstation.shared.util

import java.nio.ByteBuffer
import java.nio.ByteOrder

fun Byte.shr(bits: Int): Byte = ((this.toInt() shr bits).toByte())

fun Byte.shl(bits: Int): Byte = ((this.toInt() shl bits).toByte())

fun Short.shr(bits: Int): Short = ((this.toInt() shl bits).toShort())

fun Short.shl(bits: Int): Short = ((this.toInt() shl bits).toShort())

fun Short.toArrayByte(): Array<Byte> = arrayOf<Byte>(
    this.toByte(),
    this.shr(8).toByte()
)

fun Int.toArrayByte(): Array<Byte> = arrayOf<Byte>(
    this.toByte(),
    (this shr 8).toByte(),
    (this shr 16).toByte(),
    (this shr 24).toByte()
)

fun ByteArray.toShort(): Short {
    val bb = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN)
    bb.put(this[0])
    bb.put(this[1])
    return bb.getShort(0)
}

fun ByteArray.toInt(): Int {
    val bb = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
    bb.put(this[0])
    bb.put(this[1])
    bb.put(this[2])
    bb.put(this[3])
    return bb.getInt(0)
}



