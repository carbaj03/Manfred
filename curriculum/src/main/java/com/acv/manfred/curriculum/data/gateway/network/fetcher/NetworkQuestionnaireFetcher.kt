package com.acv.manfred.curriculum.data.gateway.network.fetcher

import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.GetQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.QuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.RemoveQuestionnaireDto

interface NetworkQuestionnaireFetcher<N> {
    fun save(
        proficiency: QuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit

    fun add(
        questionnaire: AddQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit

    fun remove(
        questionnaireDto: RemoveQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit


    fun all(
        proficiency: GetQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit

}