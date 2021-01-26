package com.apelgigit.commons.di

import com.apelgigit.commons.thread.DispatcherProvider
import com.apelgigit.commons.thread.DispatcherProviderImpl
import org.koin.dsl.module


val commonModules = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}