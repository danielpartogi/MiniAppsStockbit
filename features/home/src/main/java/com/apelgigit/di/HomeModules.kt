package com.apelgigit.di

import com.apelgigit.home.HomeViewModel
import com.apelgigit.home.WatchListVolumeViewModel
import com.apelgigit.home.WatchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModules = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { WatchListVolumeViewModel(get(),get()) }
    viewModel { WatchListViewModel(get(),get()) }
}