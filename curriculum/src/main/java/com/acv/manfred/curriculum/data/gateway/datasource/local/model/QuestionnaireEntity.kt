package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.Questionnaire
import java.util.*


@Entity(tableName = "questionaire")
data class QuestionnaireEntity(
    @PrimaryKey @ColumnInfo(name = "id") val questionaireId: String,
    var question: String,
    var answer: String
)

fun Questionnaire.toEntity() = QuestionnaireEntity(id, question ?: "", answer ?: "")
fun QuestionnaireEntity.toDomain() = Questionnaire(questionaireId, question ?: "", answer ?: "")
