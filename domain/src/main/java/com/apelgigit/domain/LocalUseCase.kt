package com.apelgigit.domain

import com.apelgigit.data.repository.PreferenceRepository

class LocalUseCase(private val repository: PreferenceRepository) {

    fun setIsLogin(boolean: Boolean) {
        repository.setIsLogin(boolean)
    }

    fun getIsLogin(): Boolean {
        return repository.getIsLogin()
    }
}