package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.MiscEducation


@Entity(tableName = "misc_education")
data class MiscEducationEntity(
    @PrimaryKey @ColumnInfo(name = "id") val miscEducationId: String,
    var miscellaneous: String
)

fun MiscEducation.toEntity(): MiscEducationEntity =
    MiscEducationEntity(id, miscellaneous ?: "")

fun MiscEducationEntity.toDomain(): MiscEducation =
    MiscEducation(miscEducationId, miscellaneous)