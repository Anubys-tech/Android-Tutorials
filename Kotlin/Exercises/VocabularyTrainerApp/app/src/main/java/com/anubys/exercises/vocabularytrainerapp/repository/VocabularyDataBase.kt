package com.anubys.exercises.vocabularytrainerapp.repository

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Vocabulary::class], version = 1, exportSchema = false)
abstract class VocabularyDataBase(): RoomDatabase() {
    abstract val vocDao:VocabularyDAO

    companion object{
        @Volatile // Bedeuteet: Sorgt dafür das dieser Wert niemals im Cach gespeichert wird, sondert nur im Hauptspeicher.
        private var INSTANCE:VocabularyDataBase? = null

        fun createInstance(application: Application):VocabularyDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        application.applicationContext,
                        VocabularyDataBase::class.java,
                        "vocabulary_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return (instance)
            }
        }
    }
}
