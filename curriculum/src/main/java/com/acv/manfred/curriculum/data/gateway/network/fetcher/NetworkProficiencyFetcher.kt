package com.acv.manfred.curriculum.data.gateway.network.fetcher

import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProficiencyEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.GetProficiencyDto

interface NetworkProficiencyFetcher<N> {

    fun all(
        proficiency: GetProficiencyDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<ProficiencyEntity>>) -> Unit
    ): Unit

}