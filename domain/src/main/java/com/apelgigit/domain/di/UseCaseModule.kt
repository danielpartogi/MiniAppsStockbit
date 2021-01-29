package com.apelgigit.domain.di

import com.apelgigit.domain.CryptoUseCase
import com.apelgigit.domain.LocalUseCase
import org.koin.dsl.module

val moduleUseCase =  module {
    single {
        CryptoUseCase(get())
    }
    single {
        LocalUseCase(get())
    }
}