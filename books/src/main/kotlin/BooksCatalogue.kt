object BooksCatalogue {
    private val catalogue = mutableListOf<BookCopy>()

    fun GetImmutableInstance(): List<BookCopy> {
        return catalogue.toList()
    }

    fun GetMutableInstance(): List<BookCopy> {
        return catalogue
    }
}