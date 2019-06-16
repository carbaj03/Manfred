package com.acv.manfred.curriculum.data.gateway.network

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.CallAsync
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkQuestionnaireFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.GetQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.QuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.RemoveQuestionnaireDto

interface NetworkQuestionnaireOperations<F, N> : CallAsync<F>, NetworkQuestionnaireFetcher<N> {
    fun QuestionnaireDto.persist(): Kind<F, ResultK<List<QuestionnaireEntity>>> =
        call(::save)

    fun AddQuestionnaireDto.persist(): Kind<F, ResultK<List<QuestionnaireEntity>>> =
        call(::add)

    fun RemoveQuestionnaireDto.delete(): Kind<F, ResultK<List<QuestionnaireEntity>>> =
        call(::remove)

    fun GetQuestionnaireDto.request(): Kind<F, ResultK<List<QuestionnaireEntity>>> =
        call(::all)
}