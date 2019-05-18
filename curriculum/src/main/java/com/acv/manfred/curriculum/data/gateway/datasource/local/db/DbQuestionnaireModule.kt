package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.left
import arrow.core.right
import arrow.core.rightIfNotNull
import arrow.core.toOption
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.QuestionaireDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity.Companion.createEmpty
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toEntity
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.ApiError
import com.acv.manfred.curriculum.domain.model.NoId
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.google.gson.JsonParseException


class DbQuestionnaireModule(val dao: QuestionaireDao) {

    companion object

    fun save(
        dto: QuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit =
        try {
            if (dao.insert(dto.toDomain().toEntity()) == -1L)
                dao.update(dto.toDomain().toEntity())
            success(dao.getQuestionaire().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun add(
        dto: AddQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit =
        try {
            success(dao.getQuestionaire().plus(createEmpty()).right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun all(
        dto: GetQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit =
        try {
            success(dao.getQuestionaire().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }


    fun remove(
        dto: RemoveQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit =
        try {
            dao.getQuestionaire(dto.questionnaire.id)?.let { dao.delete(it) }
            success(dao.getQuestionaire().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }
}
