import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToHexString
import java.io.File

object BooksCatalogue {
    private val catalogue = mutableListOf<BookCopy>()

    fun getImmutableInstance(): List<BookCopy> {
        return catalogue.toList()
    }

    fun getMutableInstance(): MutableList<BookCopy> {
        return catalogue
    }

    fun addCopy(book: BookCopy) {
        catalogue.add(book)
    }

    @ExperimentalSerializationApi
    fun decodeToMutableList(nameOfFile: String): MutableList<BookCopy> {
        File(nameOfFile).reader().use {
            return Cbor.decodeFromHexString(it.readText()) as MutableList<BookCopy>
        }
    }

    @ExperimentalSerializationApi
    fun encodeToFile(nameOfFile: String, collection: List<BookCopy>) {
        File(nameOfFile).writer().use {
            it.write(Cbor.encodeToHexString(collection))
        }
    }
}
