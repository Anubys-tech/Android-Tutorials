package com.anubys.exercises.vocabularytrainerapp.repository.database

/** @Author Created by Anubys on the 22.11.2020 */

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Vocabulary (
    @PrimaryKey(autoGenerate = true) var id:Long,
    var nativeWord:String,
    var foreignWord:String,
    var date:String,
    var status:Int
)
