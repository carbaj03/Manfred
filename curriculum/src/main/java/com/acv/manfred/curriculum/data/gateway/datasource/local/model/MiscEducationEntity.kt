package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.domain.model.WithId


@Entity(tableName = "misc_education")
data class MiscEducationEntity(
    @PrimaryKey @ColumnInfo(name = "id") val miscEducationId: String,
    var miscellaneous: String
)

fun MiscEducation.toEntity(): MiscEducationEntity =
    MiscEducationEntity(id.id, miscellaneous)

fun MiscEducationEntity.toDomain(): MiscEducation =
    MiscEducation(GenerateId(miscEducationId), miscellaneous)