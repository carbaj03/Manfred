package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acv.manfred.curriculum.domain.model.RoleProfile

@Entity(tableName = "role_profile")
data class RoleProfileEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val value: String
)

fun RoleProfileEntity.toDomain(): RoleProfile =
    RoleProfile(value)

fun RoleProfile.toEntity(): RoleProfileEntity =
    RoleProfileEntity(id.id, value)