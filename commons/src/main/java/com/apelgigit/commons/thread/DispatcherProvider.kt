package com.apelgigit.commons.thread

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val background: CoroutineDispatcher
    val commonPool: CoroutineDispatcher
}

class DispatcherProviderImpl : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val background: CoroutineDispatcher
        get() = Dispatchers.IO
    override val commonPool: CoroutineDispatcher
        get() = Dispatchers.Default
}

@ExperimentalCoroutinesApi
@VisibleForTesting
@Suppress("unused")
class TestDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val background: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val commonPool: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}