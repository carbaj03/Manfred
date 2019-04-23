package com.acv.manfred.curriculum.data.gateway.datasource.local

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.*
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ProficiencyResponse.*
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.QuestionaireDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toEntity
import com.acv.manfred.curriculum.domain.GetQuestionnaireDto
import com.acv.manfred.curriculum.domain.QuestionnaireDto
import com.acv.manfred.curriculum.domain.Result
import com.acv.manfred.curriculum.domain.model.ApiError
import com.google.gson.JsonParseException


class DbModule(val dao: QuestionaireDao) {

    companion object

    fun save(
        dto: QuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (Result<List<QuestionnaireEntity>>) -> Unit
    ): Unit =
        try {
            dao.insertAll(dto.questionnaires.map { it.toEntity() })
            success(dao.getQuestionaire().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun all(
        dto: GetQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (Result<List<QuestionnaireEntity>>) -> Unit
    ): Unit =
        try {
            success(dao.getQuestionaire().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }
}
