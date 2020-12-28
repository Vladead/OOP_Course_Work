import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToHexString
import java.io.File

object Users {
    private val users = mutableListOf<String>()

    fun getImmutableInstance(): List<String> {
        return users.toList()
    }

    fun getMutableInstance(): MutableList<String> {
        return users
    }

    @ExperimentalSerializationApi
    fun decodeToMutableList(nameOfFile: String): MutableList<String> {
        File(nameOfFile).reader().use {
            return Cbor.decodeFromHexString(it.readText()) as MutableList<String>
        }
    }

    @ExperimentalSerializationApi
    fun encodeToFile(nameOfFile: String, collection: List<String>) {
        File(nameOfFile).writer().use {
            it.write(Cbor.encodeToHexString(collection))
        }
    }
}
