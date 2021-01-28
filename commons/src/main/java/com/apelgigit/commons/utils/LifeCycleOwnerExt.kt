package com.apelgigit.commons.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

inline fun <T> Fragment.observeValue(source: LiveData<T>, crossinline function: (T) -> Unit) {
    source.observe(viewLifecycleOwner, { value ->
        value?.let { function(it) }
    })
}
