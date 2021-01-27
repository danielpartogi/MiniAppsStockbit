package com.apelgigit.miniappsstockbit

import androidx.multidex.MultiDexApplication
import com.apelgigit.miniappsstockbit.di.appComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
open class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    open fun configureDi() = startKoin {
        // use AndroidLogger as Koin Logger - default Level.INFO
        androidLogger()

        // use the Android context given there
        androidContext(this@App)

        // load properties from assets/koin.properties file
        androidFileProperties()

        modules(provideComponent()) }

    open fun provideComponent() = appComponent
}
