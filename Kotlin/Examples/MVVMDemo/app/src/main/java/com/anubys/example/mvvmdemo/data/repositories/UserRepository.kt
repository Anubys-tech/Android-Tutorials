package com.anubys.example.mvvmdemo.data.repositories

import com.anubys.example.mvvmdemo.data.firebase.FirebaseSource

/** @Author Created by Anubys on the 21.07.2020 */


class UserRepository constructor(private val firebase: FirebaseSource){

    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}
