package com.acv.manfred.curriculum.data.gateway.network

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.CallAsync
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkMiscEducationFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.MiscEducationEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.GetMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.MiscEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveMiscEducationDto

interface NetworkMiscEducationOperations<F, N> : CallAsync<F>, NetworkMiscEducationFetcher<N> {
    fun MiscEducationDto.persist(): Kind<F, ResultK<List<MiscEducationEntity>>> =
        call(::save)

    fun AddMiscEducationDto.persist(): Kind<F, ResultK<List<MiscEducationEntity>>> =
        call(::add)

    fun RemoveMiscEducationDto.delete(): Kind<F, ResultK<List<MiscEducationEntity>>> =
        call(::remove)

    fun GetMiscEducationDto.request(): Kind<F, ResultK<List<MiscEducationEntity>>> =
        call(::all)
}