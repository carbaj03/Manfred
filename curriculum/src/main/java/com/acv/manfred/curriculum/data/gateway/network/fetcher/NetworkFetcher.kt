package com.acv.manfred.curriculum.data.gateway.network.fetcher

import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.GetCvDto
import com.acv.manfred.curriculum.domain.dto.RolesDto

interface NetworkFetcher<N> {
    fun requestGet(
        getBookingDto: GetCvDto,
        error: (Throwable) -> Unit,
        success: (ResultK<ExampleResponse>) -> Unit
    ): Unit

    fun requestRoles(
        roles: RolesDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<RoleProfileResponse>>) -> Unit
    ): Unit
}