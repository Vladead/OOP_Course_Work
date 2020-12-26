object BooksCatalogue {
    private val catalogue = mutableListOf<Int>()

    fun GetImmutableInstance(): List<Int> {
        return catalogue.toList()
    }

    fun GetMutableInstance(): List<Int> {
        return catalogue
    }
}