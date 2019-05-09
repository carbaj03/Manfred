package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.MiscEducationDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.QuestionaireDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.MiscEducationEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toEntity
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.ApiError
import com.google.gson.JsonParseException


class DbMiscEducationModule(val dao: MiscEducationDao) {

    companion object

    fun save(
        dto: MiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit =
        try {
            dao.insert(dto.toDomain().toEntity())
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
            dao.insert(dto.miscEducation.toEntity())
            success(dao.getMiscEducation().right())
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
            val temp: MiscEducationEntity? = dao.getMiscEducation(dto.miscEducation)
            temp?.let { dao.delete(it) }
            success(dao.getMiscEducation().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }
}
