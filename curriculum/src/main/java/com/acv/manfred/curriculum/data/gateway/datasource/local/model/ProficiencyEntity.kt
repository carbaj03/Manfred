package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.Proficiency

@Entity(tableName = "proficiency")
data class ProficiencyEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val value: String
)

fun ProficiencyEntity.toDomain(): Proficiency =
    Proficiency(value)

fun Proficiency.toEntity(): ProficiencyEntity =
    ProficiencyEntity(id.id, value)