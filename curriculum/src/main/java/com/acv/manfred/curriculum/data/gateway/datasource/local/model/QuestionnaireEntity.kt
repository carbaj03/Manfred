package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "questionaire")
data class QuestionnaireEntity(
    @PrimaryKey @ColumnInfo(name = "id") val questionaireId: String,
    var question: String,
    var answer: String
)