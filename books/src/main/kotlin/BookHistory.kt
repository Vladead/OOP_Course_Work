import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

@Serializable
data class BookHistory(val comment: String,
                       @Serializable(with = DateAsLongSerializer::class)
                       val date: Date,
                       val newStatus: BookStatus,
                       val taker : String? = null) {
    val sss = date.time
}
