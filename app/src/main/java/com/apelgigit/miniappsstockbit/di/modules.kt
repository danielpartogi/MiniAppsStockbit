package com.apelgigit.miniappsstockbit.di

import com.apelgigit.commons.di.commonModules
import com.apelgigit.data.di.remoteModule
import com.apelgigit.data.di.repositoryModule
import com.apelgigit.data.di.webSocketModule
import com.apelgigit.di.homeModules
import com.apelgigit.domain.di.moduleUseCase
import com.apelgigit.login.di.featureAuthModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
val appComponent: List<Module> = listOf(
    repositoryModule,
    moduleUseCase,
    featureAuthModule,
    commonModules,
    homeModules,
    remoteModule("https://min-api.cryptocompare.com/"),
    webSocketModule("wss://streamer.cryptocompare.com/v2", "d69fdf30a2a97761fadddac9ad9d92127a4ddc823493d56f12f816070150bd05")
)
