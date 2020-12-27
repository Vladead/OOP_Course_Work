object BooksCatalogue {
    private val catalogue = mutableListOf<BookCopy>()

    fun getImmutableInstance(): List<BookCopy> {
        return catalogue.toList()
    }

    fun getMutableInstance(): List<BookCopy> {
        return catalogue
    }
}