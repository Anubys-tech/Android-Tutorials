package com.anubys.example.viewmodeldemo

/** @Author Created by Anubys on the 20.08.2020 */

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainActivityViewModelFactory constructor(private val name: String) : ViewModelProvider.NewInstanceFactory() {
    private val tag = MainActivityViewModelFactory::class.java.simpleName

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.d(tag,"TAG - MainActivityViewModelFactory - create()")
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return (MainActivityViewModel(name) as T)
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
