package ninja.actio.kstation.shared.test.serializers

import ninja.actio.kstation.shared.network.serialization.StringSerializer
import ninja.actio.kstation.shared.network.serialization.getString
import org.junit.jupiter.api.Test
import java.nio.ByteBuffer
import kotlin.test.assertEquals


class StringSerializerTests {
    val sourceString = "This is a source string!"

    @Test
    fun `String Serialize Length Test`() {
        val serialized = StringSerializer.serialize(sourceString)
        val lengthBytes = sourceString.toByteArray().size
        val wrapper = ByteBuffer.wrap(serialized)
        val serializedLength = wrapper.short.toInt()
        assertEquals(lengthBytes, serializedLength)
    }

    @Test
    fun `String Deserialize Test`() {
        val serialized = StringSerializer.serialize(sourceString)
        val wrapper = ByteBuffer.wrap(serialized)
        val deserialized = wrapper.getString()
        assertEquals(sourceString, deserialized)
    }

    @Test
    fun `String Deserialize In Middle Of Array Test`() {
        val serialized = StringSerializer.serialize(sourceString)
        val list = serialized.toList()
        val mutableList = mutableListOf<Byte>()
        mutableList.addAll(mutableListOf(80, 80, 80, 80, 80, 80, 80, 80))
        mutableList.addAll(list)
        mutableList.addAll(mutableListOf(80, 80, 80, 80, 80, 80, 80, 80))
        val backToArray = mutableList.toByteArray()
        val wrapper = ByteBuffer.wrap(backToArray)
        val firstLong = wrapper.long
        val string = wrapper.getString()
        val secondLong = wrapper.long
        assertEquals(sourceString, string)
        assertEquals(firstLong, secondLong)

    }

}