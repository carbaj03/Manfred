package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.MiscEducationDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.MiscEducationEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toEntity
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.AddMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.GetMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.MiscEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveMiscEducationDto
import com.acv.manfred.curriculum.domain.model.ApiError
import com.acv.manfred.curriculum.domain.model.NoId
import com.google.gson.JsonParseException


class DbMiscEducationModule(val dao: MiscEducationDao) {

    companion object

    fun save(
        dto: MiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit =
        try {
            if (dao.insert(dto.toDomain().toEntity()) == -1L)
                dao.update(dto.toDomain().toEntity())
            success(dao.getMiscEducation().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun add(
        dto: AddMiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit =
        try {
            success(dao.getMiscEducation().plus(MiscEducationEntity(NoId.id, "")).right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun all(
        dto: GetMiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit =
        try {
            success(dao.getMiscEducation().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }


    fun remove(
        dto: RemoveMiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit =
        try {
            val temp: MiscEducationEntity? = dao.getMiscEducation(dto.miscEducation.id)
            temp?.let { dao.delete(it) }
            success(dao.getMiscEducation().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }
}
