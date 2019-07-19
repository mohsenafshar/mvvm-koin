package ir.mohsenafshar.testfullkotlin.data.repo

import ir.mohsenafshar.testfullkotlin.data.model.User

class UserRepository() : UserDataSource {

    val isLocalAvailable: Boolean = false

    override fun getUser(): User =
        if (isLocalAvailable) {
            User(25, "London", "Jonas", "Carter")
        } else {
            User(37, "Mexico", "Alexandro", "Fellini")
        }
}