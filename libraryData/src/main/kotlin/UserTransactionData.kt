import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserTransactionData(
    @Serializable(with = CalendarAsLongSerializer::class)
    val until: Calendar,
    val username: String
)