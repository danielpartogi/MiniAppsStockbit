package com.apelgigit.commons.ext

import android.text.TextUtils

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isAlpha(): Boolean {
    val chars = this.toCharArray()
    for (c in chars) {
        if (!Character.isLetter(c)) {
            return false
        }
    }
    return true
}

fun String.isValidUsernameAndEmail(): Boolean {
    var isError = !this.isAlpha()
    if (isError) {
        isError = !this.isEmailValid() && !this.isAlpha()
    }
    return !isError
}

fun String.isMoreThan(count: Int): Boolean {
    return this.length > count
}