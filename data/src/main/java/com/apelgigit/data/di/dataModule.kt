package com.apelgigit.data.di

import com.apelgigit.commons.constants.APIConstants.TIMEOUT
import com.apelgigit.data.remote.services.CryptoService
import com.apelgigit.data.repository.CryptoRepository
import com.apelgigit.data.repository.CryptoRepositoryImpl
import com.apelgigit.data.websocket.ApiKeyInterceptor
import com.apelgigit.data.websocket.FlowStreamAdapter
import com.apelgigit.data.websocket.services.CryptoWSService
import com.google.gson.GsonBuilder
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.WebSocket.Factory
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"

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

@ExperimentalCoroutinesApi
fun webSocketModule(baseUrl: String, apiKey: String) = module {

    factory {
        ApiKeyInterceptor(apiKey)
    }

    single {
        Scarlet.Builder()
            .webSocketFactory(createOkHttpClient(get()).newWebSocketFactory(baseUrl))
            .addMessageAdapterFactory(GsonMessageAdapter.Factory())
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .build()
    }

    single {
        get<Scarlet>().create(CryptoWSService::class.java)
    }
}


fun createOkHttpClient(interceptor: ApiKeyInterceptor): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()

val repositoryModule = module {
    factory { CryptoRepositoryImpl(get(), get()) as CryptoRepository}
}