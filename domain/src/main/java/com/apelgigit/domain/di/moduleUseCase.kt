package com.apelgigit.domain.di

import com.apelgigit.domain.CryptoUseCase
import org.koin.dsl.module

val moduleUseCase =  module {
    single {
        CryptoUseCase(get())
    }
}