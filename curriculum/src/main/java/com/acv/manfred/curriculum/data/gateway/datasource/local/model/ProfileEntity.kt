package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import arrow.core.toOption
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.Profile
import com.acv.manfred.curriculum.domain.model.RoleProfile

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey @ColumnInfo(name = "id") val profileId: String,
    val name: String,
    val image: String?,
    val birthday: String,
    val publicLinks: List<String>,
    val roles: List<String>,
    val yearsOfExperience: Float
)

fun Profile.toEntity(): ProfileEntity =
    ProfileEntity(
        profileId = id.id,
        name = name,
        image = image.orNull(),
        birthday = birthday,
        publicLinks = publicLinks,
        roles = roles.map { it.value },
        yearsOfExperience = yearsOfExperience
    )

fun ProfileEntity.toDomain(): Profile =
    Profile(
        id = GenerateId(profileId),
        name = name,
        image = image.toOption(),
        birthday = birthday,
        publicLinks = publicLinks,
        roles = roles.map { RoleProfile(it) },
        yearsOfExperience = yearsOfExperience
    )
