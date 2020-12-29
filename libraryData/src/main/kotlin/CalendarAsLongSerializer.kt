import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

object CalendarAsLongSerializer : KSerializer<Calendar> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Calendar", PrimitiveKind.LONG)
    override fun serialize(encoder: Encoder, value: Calendar) = encoder.encodeLong(
        value.get(Calendar.YEAR).toLong() * 10000 +
                value.get(Calendar.MONTH).toLong() * 100 +
                value.get(Calendar.DAY_OF_MONTH).toLong()
    )

    override fun deserialize(decoder: Decoder): Calendar {
        var code = decoder.decodeLong()
        val toReturn = Calendar.getInstance(Locale.ENGLISH)!!
        toReturn.set(Calendar.YEAR, (code / 10000).toInt())
        code %= 10000
        toReturn.set(Calendar.MONTH, (code / 100).toInt())
        code %= 100
        toReturn.set(Calendar.DAY_OF_MONTH, (code).toInt())
        return toReturn
    }
}