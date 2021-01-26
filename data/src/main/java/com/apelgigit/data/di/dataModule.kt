package com.apelgigit.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.Network
import android.provider.SyncStateContract
import com.apelgigit.commons.constants.APIConstants.PREFERENCE_NAME
import com.apelgigit.commons.constants.APIConstants.TIMEOUT
import com.apelgigit.commons.thread.DispatcherProvider
import com.apelgigit.commons.thread.DispatcherProviderImpl
import com.apelgigit.data.remote.services.CryptoService
import com.apelgigit.data.repository.CryptoRepository
import com.apelgigit.data.repository.CryptoRepositoryImpl
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"
const val HEADER_INTERCEPTOR = "HEADER_INTERCEPTOR"

fun remoteModule(baseUrl: String) = module {
    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply { level = HttpLoggingInterceptor.Level.HEADERS }
    }


    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    factory {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(get(named(LOGGING_INTERCEPTOR)))
            .retryOnConnectionFailure(true)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    factory { get<Retrofit>().create(CryptoService::class.java) }
}

val repositoryModule = module {
    factory { CryptoRepositoryImpl(get())}
}