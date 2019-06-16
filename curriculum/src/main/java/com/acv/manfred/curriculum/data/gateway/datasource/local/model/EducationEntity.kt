package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.Education
import com.acv.manfred.curriculum.domain.model.GenerateId


@Entity(tableName = "education")
data class EducationEntity(
    @PrimaryKey @ColumnInfo(name = "id") val educationId: String,
    var institution: String,
    var study: String,
    var from: String,
    var until: String
//    var achievements: List<String>
)

fun Education.toEntity(): EducationEntity =
    EducationEntity(id.id, institution, study, from, until)

fun EducationEntity.toDomain(): Education =
    Education(GenerateId(educationId), institution, study, from, until)