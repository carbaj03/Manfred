package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.LanguageDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.LanguageEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.LanguageEntity.Companion.createEmpty
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toEntity
import com.acv.manfred.curriculum.domain.dto.AddLanguageDto
import com.acv.manfred.curriculum.domain.dto.GetLanguageDto
import com.acv.manfred.curriculum.domain.dto.LanguageDto
import com.acv.manfred.curriculum.domain.dto.RemoveLanguageDto
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.ApiError
import com.google.gson.JsonParseException


class DbLanguageModule(val dao: LanguageDao) : ModuleOps {

    companion object

    fun save(
        dto: LanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit =
        try {
            dao.insert(dto.toDomain().toEntity())
            success(dao.getLanguage().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun add(
        dto: AddLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit =
        try {
            success(dao.getLanguage().plus(createEmpty()).right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun all(
        dto: GetLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit =
        try {
            success(dao.getLanguage().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }


    fun remove(
        dto: RemoveLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit =
        try {
            dao.getLanguage(dto.id.id)?.let { dao.delete(it) }
            success(dao.getLanguage().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }
}
