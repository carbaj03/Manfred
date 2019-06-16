package com.acv.manfred.curriculum.data.gateway.network.fetcher

import com.acv.manfred.curriculum.data.gateway.datasource.local.model.LanguageEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddLanguageDto
import com.acv.manfred.curriculum.domain.dto.GetLanguageDto
import com.acv.manfred.curriculum.domain.dto.LanguageDto
import com.acv.manfred.curriculum.domain.dto.RemoveLanguageDto

interface NetworkLanguageFetcher<N> {
    fun save(
        proficiency: LanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit

    fun add(
        questionnaire: AddLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit

    fun remove(
        questionnaireDto: RemoveLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit


    fun all(
        proficiency: GetLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit

}