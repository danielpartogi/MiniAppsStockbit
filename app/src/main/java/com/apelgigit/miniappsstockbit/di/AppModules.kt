package com.apelgigit.miniappsstockbit.di

import com.apelgigit.commons.di.commonModules
import com.apelgigit.data.di.localModule
import com.apelgigit.data.di.remoteModule
import com.apelgigit.data.di.repositoryModule
import com.apelgigit.data.di.webSocketModule
import com.apelgigit.di.homeModules
import com.apelgigit.domain.di.moduleUseCase
import com.apelgigit.login.di.featureAuthModule
import com.apelgigit.miniappsstockbit.BuildConfig.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
val appComponent: List<Module> = listOf(
    localModule,
    repositoryModule,
    moduleUseCase,
    featureAuthModule,
    commonModules,
    homeModules,
    remoteModule(BASE_URL),
    webSocketModule(BASE_URL_WS, COMPARE_CRYPTO_KEY)
)
