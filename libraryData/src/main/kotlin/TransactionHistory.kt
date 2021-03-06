import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class TransactionHistory(
    val comment: String,
    @Serializable(with = CalendarAsLongSerializer::class)
    val date: Calendar,
    val newStatus: BookStatus,
    val user: UserTransactionData?,
) {
    override fun toString(): String {
        return "$comment on ${date.get(Calendar.YEAR)}/${date.get(Calendar.MONTH) + 1}/${date.get(Calendar.DAY_OF_MONTH)}" +
                if (user != null) {
                    " by ${user.username} until " +
                            "${user.until.get(Calendar.YEAR)}/${user.until.get(Calendar.MONTH) + 1}/${
                                user.until.get(Calendar.DAY_OF_MONTH)
                            }"
                } else {
                    ""
                } +
                "; now $newStatus"
    }
}