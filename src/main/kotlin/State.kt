enum class State (val access: Boolean) {
    MainMenu(false),
    User(false),
    UserLogin(false),
    UserError(false),
    Administrator(true)
}