package com.anubys.exercises.vocabularytrainerapp.repository.database

/** @Author Created by Anubys on the 22.11.2020 */

import android.app.Application
import android.util.Log

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Vocabulary::class], version = 1, exportSchema = false)
abstract class VocabularyDataBase(): RoomDatabase() {

    abstract val vocDao: VocabularyDAO


    //* *************************************************** *
    //*                     O B J E C T                     *
    //* *************************************************** *
    companion object{
        private val tag = VocabularyDataBase::class.java.simpleName
        @Volatile // Bedeuteet: Sorgt dafür das dieser Wert niemals im Cach gespeichert wird, sondert nur im Hauptspeicher.
        private var INSTANCE: VocabularyDataBase? = null

        fun createInstance(application: Application): VocabularyDataBase {
            Log.d(tag, "TAG - VocabularyDataBase - createInstance()")

            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(application.applicationContext, VocabularyDataBase::class.java, "vocabulary_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return (instance)
            }
        }
    }
}
