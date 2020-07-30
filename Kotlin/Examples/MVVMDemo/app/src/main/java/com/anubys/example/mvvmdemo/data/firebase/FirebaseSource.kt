package com.anubys.example.mvvmdemo.data.firebase

import android.util.Log

import com.google.firebase.auth.FirebaseAuth

import io.reactivex.Completable

/** @Author Created by Anubys on the 21.07.2020 */


class FirebaseSource {
    private val tag = FirebaseSource::class.java.simpleName

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            Log.d(tag,"TAG - FirebaseSource - login()")
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            Log.d(tag,"TAG - FirebaseSource - register()")
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}
