package com.apelgigit.miniappsstockbit.di

import com.apelgigit.commons.di.commonModules
import com.apelgigit.data.di.remoteModule
import com.apelgigit.data.di.repositoryModule
import com.apelgigit.domain.di.moduleUseCase
import com.apelgigit.login.di.featureAuthModule
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
    featureAuthModule,
    commonModules,
    remoteModule("https://min-api.cryptocompare.com/"),
    repositoryModule,
    moduleUseCase
)
