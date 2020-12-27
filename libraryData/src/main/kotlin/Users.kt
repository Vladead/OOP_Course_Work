object Users {
    private val users = mutableListOf<String>()

    fun getImmutableInstance(): List<String> {
        return users.toList()
    }

    fun getMutableInstance(): List<String> {
        return users
    }
}