package com.acv.manfred.curriculum.data.gateway.network

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.CallAsync
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkProficiencyFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProficiencyEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.GetProficiencyDto

interface NetworkProficiencyOperations<F, N> : CallAsync<F>, NetworkProficiencyFetcher<N> {

    fun GetProficiencyDto.request(): Kind<F, ResultK<List<ProficiencyEntity>>> =
        call(::all)
}