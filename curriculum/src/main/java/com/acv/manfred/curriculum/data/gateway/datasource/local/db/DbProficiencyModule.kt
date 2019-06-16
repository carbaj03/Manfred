package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.ProficiencyDao
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProficiencyEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.GetProficiencyDto
import com.acv.manfred.curriculum.domain.model.*
import com.google.gson.JsonParseException


class DbProficiencyModule(val dao: ProficiencyDao) {

    companion object

    fun all(
        dto: GetProficiencyDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<ProficiencyEntity>>) -> Unit
    ): Unit =
        try {
//            success(dao.getProficiency().right())
            success(mockProficiency().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }


    private fun mockProficiency(): List<ProficiencyEntity> =
        listOf(
            Elementary,
            LimitedWorking,
            ProfessionalWorking,
            FullProfessional,
            NativeOrBilingual
        ).map { ProficiencyEntity(it.id.id, it.value) }
}
