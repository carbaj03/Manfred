package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.Questionnaire


@Entity(tableName = "questionnaire")
data class QuestionnaireEntity(
    @PrimaryKey @ColumnInfo(name = "id") val questionnaireId: String,
    var question: String,
    var answer: String
)

fun Questionnaire.toEntity(): QuestionnaireEntity =
    QuestionnaireEntity(id, question ?: "", answer ?: "")

fun QuestionnaireEntity.toDomain(): Questionnaire =
    Questionnaire(questionnaireId, question, answer)
