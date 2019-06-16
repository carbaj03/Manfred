package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.right
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.EducationDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.EducationEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toEntity
import com.acv.manfred.curriculum.domain.dto.AddEducationDto
import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.dto.GetEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveEducationDto
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.NoId


class DbEducationModule(val dao: EducationDao): ModuleOps {

    suspend fun save(
        dto: EducationDto
    ): ResultK<List<EducationEntity>> =
        either {
            if (dao.insert(dto.toDomain().toEntity()) == -1L)
                dao.update(dto.toDomain().toEntity())
            dao.getEducation().right()
        }


    suspend fun add(
        dto: AddEducationDto
    ): ResultK<List<EducationEntity>> =
        either {
            dao.getEducation().plus(EducationEntity(NoId.id, "", "", "", "")).right()
        }

    suspend fun all(
        dto: GetEducationDto
    ): ResultK<List<EducationEntity>> =
        either {
            dao.getEducation().right()
        }

    suspend fun remove(
        dto: RemoveEducationDto
    ): ResultK<List<EducationEntity>> =
        either {
            val temp: EducationEntity? = dao.getEducation(dto.miscEducation.id)
            temp?.let { dao.delete(it) }
            dao.getEducation().right()
        }

    companion object
}
