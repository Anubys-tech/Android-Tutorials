package com.anubys.exercises.vocabularytrainerapp.repository

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
/**
 * Dieses Interface regelt die Kommunikation
 * löschen, einfügen, abrufen,
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
