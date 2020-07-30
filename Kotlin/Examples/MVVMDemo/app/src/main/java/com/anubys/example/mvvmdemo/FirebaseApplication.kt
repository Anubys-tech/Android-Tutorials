package com.anubys.example.mvvmdemo

import android.app.Application
import android.util.Log

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

import com.anubys.example.mvvmdemo.data.firebase.FirebaseSource
import com.anubys.example.mvvmdemo.data.repositories.UserRepository
import com.anubys.example.mvvmdemo.ui.auth.AuthViewModelFactory
import com.anubys.example.mvvmdemo.ui.home.HomeViewModelFactory

/** @Author Created by Anubys on the 21.07.2020 */


open class FirebaseApplication : Application(), KodeinAware {
    private val tag = FirebaseApplication::class.java.simpleName

    override val kodein = Kodein.lazy {
        Log.d(tag,"TAG - FirebaseApplication - Kodein.lazy")
        import(androidXModule(this@FirebaseApplication))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }

    }
}
