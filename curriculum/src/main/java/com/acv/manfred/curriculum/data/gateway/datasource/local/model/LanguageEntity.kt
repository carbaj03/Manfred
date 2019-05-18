package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.*


@Entity(tableName = "language")
data class LanguageEntity(
    @PrimaryKey @ColumnInfo(name = "id") val languageId: String,
    var language: String,
    var idProficiency: String,
    var valueProficiency: String
){
    companion object {
        fun createEmpty(): LanguageEntity =
            LanguageEntity(languageId = NoId.id, language = "", idProficiency = "", valueProficiency = "")
    }
}

fun Language.toEntity(): LanguageEntity =
    LanguageEntity(id.id, language, proficiency.id.id, proficiency.value)

fun LanguageEntity.toDomain(): Language =
    Language(id = GenerateId(languageId), language = language, proficiency = Proficiency(valueProficiency))

