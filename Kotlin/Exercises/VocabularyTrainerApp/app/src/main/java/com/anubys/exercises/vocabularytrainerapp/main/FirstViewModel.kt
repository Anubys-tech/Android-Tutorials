package com.anubys.exercises.vocabularytrainerapp.main

/** @Author Created by Anubys on the 22.11.2020 */

import android.app.Application
import android.util.Log

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import com.anubys.exercises.vocabularytrainerapp.repository.database.Vocabulary
import com.anubys.exercises.vocabularytrainerapp.repository.repository.AppRepository

import kotlinx.coroutines.launch

import java.text.SimpleDateFormat
import java.util.*


class FirstViewModel(application: Application) : AndroidViewModel(application) {
    private val tagViewModel = FirstViewModel::class.java.simpleName

    private val repository = AppRepository(application)
    private var liveVocabularyList = repository.getLiveDataVocabulary()


    //* *************************************************** *
    //*            H E L P E R  -  M E T H O D S            *
    //* *************************************************** *
    fun insertVocabulary(nativeWord: String, foreignWord: String) {
        Log.d(tagViewModel, "TAG - FirstViewModel - insertVocabulary()")

        viewModelScope.launch {
            val vocabulary = Vocabulary(0L, nativeWord, foreignWord, Date().toStringFormat(),0)
            repository.insertVocabulary(vocabulary)
        }
    }

    fun updateVocabulary(vocabulary: Vocabulary) {
        Log.d(tagViewModel, "TAG - FirstViewModel - updateVocabulary()")

        viewModelScope.launch {
            repository.updateVocabulary(vocabulary)
        }
    }

    fun deleteVocabulary(vocabulary: Vocabulary) {
        Log.d(tagViewModel, "TAG - FirstViewModel - deleteVocabulary()")

        viewModelScope.launch {
            repository.deleteVocabulary(vocabulary)
        }
    }

    fun getVocabularyById(vocabularyId: Long): Vocabulary? {
        Log.d(tagViewModel, "TAG - FirstViewModel - getVocabularyById()")

        var vocabulary:Vocabulary? = null
        viewModelScope.launch {
            vocabulary = repository.getVocabularyById(vocabularyId)
        }

        return (vocabulary)
    }

    fun getAllVocabulary(): List<Vocabulary>? {
        Log.d(tagViewModel, "TAG - FirstViewModel - getAllVocabulary()")

        var vocabularyList:List<Vocabulary>? = null
        viewModelScope.launch {
            vocabularyList =  repository.getAllVocabulary()
        }

        return (vocabularyList)
    }

    fun getLiveVocabularyList(): LiveData<List<Vocabulary>> = liveVocabularyList

    private fun Date.toStringFormat(pattern:String="dd.MM.yyyy"): String {
        Log.d(tagViewModel, "TAG - FirstViewModel - toStringFormat()")

        return (SimpleDateFormat(pattern, Locale.GERMANY).format(this))
    }
}
