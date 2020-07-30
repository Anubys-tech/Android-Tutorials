package com.anubys.example.mvvmdemo.ui.auth

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.anubys.example.mvvmdemo.data.repositories.UserRepository

/** @Author Created by Anubys on the 21.07.2020 */


@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory constructor(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    private val tag = AuthViewModelFactory::class.java.simpleName

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.d(tag,"TAG - AuthViewModelFactory - create()")
        return AuthViewModel(repository) as T
    }
}
