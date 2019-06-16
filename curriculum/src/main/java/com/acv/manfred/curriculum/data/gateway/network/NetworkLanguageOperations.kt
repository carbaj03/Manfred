package com.acv.manfred.curriculum.data.gateway.network

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.CallAsync
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkLanguageFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.LanguageEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddLanguageDto
import com.acv.manfred.curriculum.domain.dto.GetLanguageDto
import com.acv.manfred.curriculum.domain.dto.LanguageDto
import com.acv.manfred.curriculum.domain.dto.RemoveLanguageDto

interface NetworkLanguageOperations<F, N> : CallAsync<F>, NetworkLanguageFetcher<N> {
    fun LanguageDto.persist(): Kind<F, ResultK<List<LanguageEntity>>> =
        call(::save)

    fun AddLanguageDto.persist(): Kind<F, ResultK<List<LanguageEntity>>> =
        call(::add)

    fun RemoveLanguageDto.delete(): Kind<F, ResultK<List<LanguageEntity>>> =
        call(::remove)

    fun GetLanguageDto.request(): Kind<F, ResultK<List<LanguageEntity>>> =
        call(::all)
}