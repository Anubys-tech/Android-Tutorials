package com.anubys.exercises.vocabularytrainerapp.repository.database

/** @Author Created by Anubys on the 22.11.2020 */

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
/**
 * DE: Dieses Interface regelt die Kommunikation zwischen Datenbank und GUI.
 * EN: This interface controls the communication between database and GUI.
 */
interface VocabularyDAO {
    @Insert
    suspend fun insert(vocabulary: Vocabulary)

    @Delete
    suspend fun delete(vocabulary: Vocabulary)

    @Update
    suspend fun update(vocabulary: Vocabulary)

    @Query("SELECT * FROM Vocabulary WHERE id = :vocabularyId")
    suspend fun getVocById(vocabularyId: Long): Vocabulary

    @Query("SELECT * FROM Vocabulary")
    suspend fun getVocList(): List<Vocabulary>

    @Query("SELECT * FROM Vocabulary")
    fun getLiveDataVocList(): LiveData<List<Vocabulary>>
}
