package ir.mohsenafshar.testfullkotlin.data.repo

import ir.mohsenafshar.testfullkotlin.data.model.User

interface UserDataSource {
    fun getUser(): User
}