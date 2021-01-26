package com.apelgigit.miniappsstockbit.di

import com.apelgigit.commons.di.commonModules
import com.apelgigit.login.di.featureAuthModule
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
    featureAuthModule,
    commonModules
)
