package com.acv.manfred.curriculum.data.gateway.network.fetcher

import com.acv.manfred.curriculum.data.gateway.datasource.local.model.MiscEducationEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.GetMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.MiscEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveMiscEducationDto

interface NetworkMiscEducationFetcher<N> {
    fun save(
        proficiency: MiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit

    fun add(
        questionnaire: AddMiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit

    fun remove(
        questionnaireDto: RemoveMiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit


    fun all(
        proficiency: GetMiscEducationDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<MiscEducationEntity>>) -> Unit
    ): Unit

}