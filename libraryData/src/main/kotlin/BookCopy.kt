import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BookCopy(val name: String, val authors: Collection<String>, val image: ByteArray, val description: String) {
    private val transactionHistory = mutableListOf<TransactionHistory>()

    fun getLastTransaction(): TransactionHistory = transactionHistory.first()

    fun getAllTransaction(): List<TransactionHistory> = transactionHistory.toList()

    fun addNewTransaction(
        comment: String,
        date: Calendar,
        status: BookStatus,
        userData: UserTransactionData? = null
    ) =
        transactionHistory.add(
            0,
            TransactionHistory(
                comment,
                date,
                status,
                userData
            )
        )
}
