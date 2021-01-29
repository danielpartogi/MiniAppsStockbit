package com.apelgigit.commons.di

import com.apelgigit.commons.utils.DispatcherProvider
import com.apelgigit.commons.utils.DispatcherProviderImpl
import org.koin.dsl.module


val commonModules = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}