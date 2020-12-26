import kotlinx.serialization.Serializable
import java.awt.image.BufferedImage

@Serializable
data class BookCopy(val name: String, val authors: Collection<String>, val image: ByteArray, val description: String) {
    val transactionHistory = mutableListOf<BookHistory>()
}
