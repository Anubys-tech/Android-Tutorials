package com.anubys.example.mvvmdemo.ui.auth

import android.content.Intent
import android.util.Log
import android.view.View

import androidx.lifecycle.ViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import com.anubys.example.mvvmdemo.data.repositories.UserRepository

/** @Author Created by Anubys on the 21.07.2020 */


class AuthViewModel constructor(private val repository: UserRepository) : ViewModel() {
    private val tag = AuthViewModel::class.java.simpleName

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()
    val user by lazy { repository.currentUser() }


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCleared() {
        Log.d(tag,"TAG - AuthViewModel - onCleared()")
        super.onCleared()
        disposables.dispose()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun login() {
        Log.d(tag,"TAG - AuthViewModel - login()")
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        authListener?.onStarted()

        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun register() {
        Log.d(tag,"TAG - AuthViewModel - register()")
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        authListener?.onStarted()
        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun goToRegister(view: View) {
        Log.d(tag,"TAG - AuthViewModel - goToRegister()")
        Intent(view.context, RegisterActivity::class.java).also { view.context.startActivity(it) }
    }

    fun goToLogin(view: View) {
        Log.d(tag,"TAG - AuthViewModel - goToLogin()")
        Intent(view.context, LoginActivity::class.java).also { view.context.startActivity(it) }
    }
}
