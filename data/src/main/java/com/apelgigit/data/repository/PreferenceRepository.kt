package com.apelgigit.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.apelgigit.commons.constants.Constants.IS_LOGIN
import com.apelgigit.commons.constants.Constants.PREFERENCE_NAME

interface PreferenceRepository {
    fun setIsLogin(boolean: Boolean)
    fun getIsLogin(): Boolean
}

class PreferenceRepositoryImpl(application: Application): PreferenceRepository {
    private val prefs: SharedPreferences by lazy {
        application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    override fun setIsLogin(boolean: Boolean) {
        prefs.edit().putBoolean(IS_LOGIN, boolean).apply()
    }

    override fun getIsLogin():Boolean {
       return prefs.getBoolean(IS_LOGIN, false)
    }


}