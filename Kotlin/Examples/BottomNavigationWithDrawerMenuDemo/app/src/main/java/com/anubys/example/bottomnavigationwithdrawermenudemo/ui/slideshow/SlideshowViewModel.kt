package com.anubys.example.bottomnavigationwithdrawermenudemo.ui.slideshow

/** @Author Created by Anubys on the 14.08.2020 */

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
}
