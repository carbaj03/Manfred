package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.syntax.collections.firstOption
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProfileDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProfileEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toEntity
import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.NoId
import com.acv.manfred.curriculum.domain.model.NotFoundError


class DbProfileModule(val dao: ProfileDao): ModuleOps {

    suspend fun save(
        dto: ProfileDto
    ): ResultK<ProfileEntity> =
        either {
            if (dao.insert(dto.toDomain().toEntity()) == -1L)
                dao.update(dto.toDomain().toEntity())
            dao.getProfile().first().right()
        }


    suspend fun add(
        dto: AddProfileDto
    ): ResultK<ProfileEntity> =
        either {
           ProfileEntity(profileId = NoId.id, name = "", image = "", birthday = "", yearsOfExperience = 0f, roles = listOf(), publicLinks = listOf()).right()
        }

    suspend fun all(
        dto: GetProfileDto
    ): ResultK<ProfileEntity> =
        either {
            dao.getProfile().firstOrNull()?.right() ?: NotFoundError.left()
        }

    suspend fun remove(
        dto: RemoveProfileDto
    ): ResultK<ProfileEntity> =
        either {
            val temp: ProfileEntity? = dao.getProfile(dto.id.id)
            temp?.let { dao.delete(it) }
            dao.getProfile().firstOrNull()?.right() ?: NotFoundError.left()
        }

    companion object
}
