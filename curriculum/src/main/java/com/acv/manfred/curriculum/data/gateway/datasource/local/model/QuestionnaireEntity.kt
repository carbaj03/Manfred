package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.NoId
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.domain.model.WithId


@Entity(tableName = "questionnaire")
data class QuestionnaireEntity(
    @PrimaryKey @ColumnInfo(name = "id") val questionnaireId: String,
    var question: String,
    var answer: String
) {
    companion object {
        fun createEmpty(): QuestionnaireEntity =
            QuestionnaireEntity(NoId.id, "", "")
    }
}

fun Questionnaire.toEntity(): QuestionnaireEntity =
    QuestionnaireEntity(id.id, question, answer)

fun QuestionnaireEntity.toDomain(): Questionnaire =
    Questionnaire(GenerateId(questionnaireId), question, answer)
