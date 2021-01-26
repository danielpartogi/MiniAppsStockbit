package com.apelgigit.login.di

import com.apelgigit.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureAuthModule  = module {
   viewModel { LoginViewModel(get(), get()) }
}