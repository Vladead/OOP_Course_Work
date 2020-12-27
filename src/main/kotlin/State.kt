enum class State (val access: Boolean) {
    Login(false),
    User(false),
    UserLogin(false),
    UserError(false),
    Administrator(true)
}