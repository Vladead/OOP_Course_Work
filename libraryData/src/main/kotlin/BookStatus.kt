import kotlinx.serialization.Serializable

@Serializable
enum class BookStatus {
    AVAILABLE,
    TAKEN,
    UTILIZED,
    COMING_SOON
}