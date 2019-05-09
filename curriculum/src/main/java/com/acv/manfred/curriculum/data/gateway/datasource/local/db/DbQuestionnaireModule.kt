package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.QuestionaireDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toEntity
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.ApiError
import com.google.gson.JsonParseException


class DbQuestionnaireModule(val dao: QuestionaireDao) {

    companion object

    fun save(
        dto: QuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit =
        try {
            dao.insert(dto.toDomain().toEntity())
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
            dao.insert(dto.questionnaire.toEntity())
            success(dao.getQuestionaire().right())
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
            val temp: QuestionnaireEntity? = dao.getQuestionaire(dto.questionnaire)
            temp?.let { dao.delete(it) }
//            dao.delete(temp!!)
            success(dao.getQuestionaire().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }
}
