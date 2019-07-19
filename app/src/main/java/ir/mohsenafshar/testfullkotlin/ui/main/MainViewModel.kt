package ir.mohsenafshar.testfullkotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.mohsenafshar.testfullkotlin.data.model.User
import ir.mohsenafshar.testfullkotlin.data.repo.UserDataSource

class MainViewModel(val userRepository: UserDataSource) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun getUser(): User =
        userRepository.getUser()
}