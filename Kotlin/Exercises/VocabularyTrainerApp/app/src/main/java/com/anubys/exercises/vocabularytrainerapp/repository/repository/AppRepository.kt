package com.anubys.exercises.vocabularytrainerapp.repository.repository

/** @Author Created by Anubys on the 22.11.2020 */

import android.app.Application
import android.util.Log

import androidx.lifecycle.LiveData
import com.anubys.exercises.vocabularytrainerapp.dialog.DialogInput

import com.anubys.exercises.vocabularytrainerapp.repository.database.Vocabulary
import com.anubys.exercises.vocabularytrainerapp.repository.database.VocabularyDAO
import com.anubys.exercises.vocabularytrainerapp.repository.database.VocabularyDataBase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppRepository(application: Application) {
    private val tagRepository = AppRepository::class.java.simpleName

    private val vocabularyDao: VocabularyDAO


    //* *************************************************** *
    //*                       I N I T                       *
    //* *************************************************** *
    init {
        Log.d(tagRepository, "TAG - AppRepository - init()")

        val database = VocabularyDataBase.createInstance(application)
        vocabularyDao = database.vocDao
    }


    //* *************************************************** *
    //*            H E L P E R  -  M E T H O D S            *
    //* *************************************************** *
    /**
     * DE: Fügt eine Vokabel in die Datenbank ein.
     * EN: Inserts a vocabulary into the database.
     */
    suspend fun insertVocabulary(vocabulary: Vocabulary) {
        Log.d(tagRepository, "TAG - AppRepository - insertVocabulary()")

        withContext(Dispatchers.IO) {
            vocabularyDao.insert(vocabulary)
        }
    }

    /**
     * DE: Löscht eine Vakabel aus der Datenbank.
     * EN: Deletes a vacancy from the database.
     */
    suspend fun deleteVocabulary(vocabulary: Vocabulary) {
        Log.d(tagRepository, "TAG - AppRepository - deleteVocabulary()")

        withContext(Dispatchers.IO) {
            vocabularyDao.delete(vocabulary)
        }
    }

    /**
     * DE: Aktuallisiert eine Änderung der Vokabel in der Datenbank.
     * EN: Updates a change of vocabulary in the database.
     */
    suspend fun updateVocabulary(vocabulary: Vocabulary) {
        Log.d(tagRepository, "TAG - AppRepository - updateVocabulary()")

        withContext(Dispatchers.IO) {
            vocabularyDao.update(vocabulary)
        }
    }

    /**
     * DE: Holt eine Vokabel aus der Datenbank durch den Übergabeparameter-ID.
     * EN: Gets a vocabulary from the database by the transfer parameter ID.
     */
    suspend fun getVocabularyById(vocabularyId: Long): Vocabulary? {
        Log.d(tagRepository, "TAG - AppRepository - getVocabularyById()")

        var vocabulary: Vocabulary? = null
        withContext(Dispatchers.IO) {
            vocabulary = vocabularyDao.getVocById(vocabularyId)
        }

        return (vocabulary)
    }

    /**
     * DE: Holt alle Vokabeln aus der Datenbank in einer Liste.
     * EN: Gets all vocabulary from the database in a list.
     */
    suspend fun getAllVocabulary(): List<Vocabulary>? {
        Log.d(tagRepository, "TAG - AppRepository - getAllVocabulary()")

        var vocabularyList: List<Vocabulary>? = null
        withContext(Dispatchers.IO) {
            vocabularyList = vocabularyDao.getVocList()
        }

        return (vocabularyList)
    }

    /**
     * DE: Überwacht und benachrichtigt bei Änderung der Vokabelliste.
     * EN: Monitors and notifies when the vocabulary list changes.
     */
    fun getLiveDataVocabulary(): LiveData<List<Vocabulary>> = vocabularyDao.getLiveDataVocList()
}
