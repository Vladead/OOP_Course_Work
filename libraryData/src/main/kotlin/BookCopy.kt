import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BookCopy(val name: String, val authors: String, val imagePath: String, val description: String) {
    private val transactionHistory = mutableListOf<BookHistory>()

    fun getLastTransaction(): BookHistory = transactionHistory.first()

    fun getAllTransaction(): List<BookHistory> = transactionHistory.toList()

    fun addNewTransaction(
        comment: String,
        date: Calendar,
        status: BookStatus,
        userData: UserTransactionData? = null
    ) =
        transactionHistory.add(
            0,
            BookHistory(
                comment,
                date,
                status,
                userData
            )
        )
}
