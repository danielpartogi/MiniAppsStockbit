package com.apelgigit.commons.ext

import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.ImageButton

fun View.clickWithDebounce(debounceTime: Long = 1000L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}
