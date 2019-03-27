package ninja.actio.kstation.shared.test.serializers

import ninja.actio.kstation.shared.network.serialization.LongSerializer
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class LongSerializerTest {
    val sourceLong = 10000000L

    @Test
    fun `Long Serialization Test`() {
        val serialized = LongSerializer.longToBytes(sourceLong)
        println(serialized.toList())
        val deserialized = LongSerializer.bytesToLong(serialized)
        assertEquals(sourceLong, deserialized)
    }
}