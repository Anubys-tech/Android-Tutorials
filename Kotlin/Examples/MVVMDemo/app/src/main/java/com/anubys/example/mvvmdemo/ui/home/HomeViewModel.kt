package com.anubys.example.mvvmdemo.ui.home

import android.util.Log
import android.view.View

import androidx.lifecycle.ViewModel

import com.anubys.example.mvvmdemo.data.repositories.UserRepository
import com.anubys.example.mvvmdemo.utils.startLoginActivity

/** @Author Created by Anubys on the 21.07.2020 */


class HomeViewModel constructor(private val repository: UserRepository) : ViewModel() {
    private val tag = HomeViewModel::class.java.simpleName

    val user by lazy { repository.currentUser() }

    fun logout(view: View) {
        Log.d(tag,"TAG - HomeViewModel - logout()")
        repository.logout()
        view.context.startLoginActivity()
    }
}
