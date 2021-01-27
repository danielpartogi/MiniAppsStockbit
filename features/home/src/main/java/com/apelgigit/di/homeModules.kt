package com.apelgigit.di

import com.apelgigit.addCrypto.AddCryptoViewModel
import com.apelgigit.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModules = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AddCryptoViewModel(get(), get()) }
}