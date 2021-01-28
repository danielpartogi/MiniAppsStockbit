package com.apelgigit.commons.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.apelgigit.commons.utils.Event
import com.apelgigit.commons.utils.DispatcherProvider
import com.apelgigit.commons.utils.RequestStatus
import com.apelgigit.commons.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(
    private val dispatcher: DispatcherProvider
): ViewModel(), CoroutineScope {

    private val supervisorJob = SupervisorJob()

    var isNetworkAvailable = MutableLiveData<Boolean>()

    val networkObserver = Observer<Boolean> {
        if (!it && supervisorJob.isActive) {
            supervisorJob.cancelChildren(CancellationException("No network"))
            // change UI - hide progress / show Retry button etc.
        }
    }

    var errorType = ErrorType.SNACKBAR

    protected var _errorEvent: MutableLiveData<Event<Resource<String>>> = MutableLiveData()
    val errorEvent: LiveData<Event<Resource<String>>> get() = _errorEvent

    init {
        isNetworkAvailable.observeForever(networkObserver)
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.main + supervisorJob

    override fun onCleared() {
        super.onCleared()
        isNetworkAvailable.removeObserver(networkObserver)
        coroutineContext.cancel()
    }

    fun setupError(errorType: ErrorType, errorEvent: Event<Resource<String>>){
        this.errorType = errorType
        _errorEvent.value = errorEvent
    }

    fun setupError(errorType: ErrorType, error: String?){
        this.errorType = errorType
        _errorEvent.value = Event(Resource(data = "", requestStatus = RequestStatus.ERROR, message = error))
    }
}

enum class ErrorType {
    ALERT_DIALOG,
    SNACKBAR
}

