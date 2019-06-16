package com.acv.manfred.curriculum.data.gateway.network

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.CallAsync
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.GetCvDto
import com.acv.manfred.curriculum.domain.dto.RolesDto

interface NetworkOperations<F, N> : CallAsync<F>, NetworkFetcher<N> {
    fun GetCvDto.request(): Kind<F, ResultK<ExampleResponse>> =
        call(::requestGet)

    fun RolesDto.request(): Kind<F, ResultK<List<RoleProfileResponse>>> =
        call(::requestRoles)
}