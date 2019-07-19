package ir.mohsenafshar.testfullkotlin.di

import ir.mohsenafshar.testfullkotlin.data.repo.UserDataSource
import ir.mohsenafshar.testfullkotlin.data.repo.UserRepository
import ir.mohsenafshar.testfullkotlin.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<UserDataSource> { UserRepository() }

    viewModel { MainViewModel(get()) }

}