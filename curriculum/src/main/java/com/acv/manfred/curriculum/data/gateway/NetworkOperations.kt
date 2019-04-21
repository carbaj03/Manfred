package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.extension
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ProficiencyResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.domain.GetCvDto
import com.acv.manfred.curriculum.domain.ProficiencyDto
import com.acv.manfred.curriculum.domain.Result
import com.acv.manfred.curriculum.domain.RolesDto


@extension
interface NetworkModuleNetworkFetcher : NetworkFetcher<ApiModule> {
    companion object {
        private val apiModule = ApiModule()
    }

    override fun requestGet(getBookingDto: GetCvDto, error: (Throwable) -> Unit, success: (Result<ExampleResponse>) -> Unit) =
        apiModule.requestGet(getBookingDto, error, success)

    override fun requestRoles(roles: RolesDto, error: (Throwable) -> Unit, success: (Result<List<RoleProfileResponse>>) -> Unit) =
        apiModule.requestRoles(roles, error, success)

    override fun requestProficiency(proficiency: ProficiencyDto, error: (Throwable) -> Unit, success: (Result<List<ProficiencyResponse>>) -> Unit) =
        apiModule.requestProficiency(proficiency, error, success)
}

fun ApiModule.Companion.networkFetcher(): NetworkFetcher<ApiModule> =
    object : NetworkModuleNetworkFetcher {}

interface NetworkFetcher<N> {
    fun requestGet(
        getBookingDto: GetCvDto,
        error: (Throwable) -> Unit,
        success: (Result<ExampleResponse>) -> Unit
    ): Unit

    fun requestRoles(
        roles: RolesDto,
        error: (Throwable) -> Unit,
        success: (Result<List<RoleProfileResponse>>) -> Unit
    ): Unit

    fun requestProficiency(
        proficiency: ProficiencyDto,
        error: (Throwable) -> Unit,
        success: (Result<List<ProficiencyResponse>>) -> Unit
    ): Unit
}

interface NetworkOperations<F, N> : CallAsync<F>, NetworkFetcher<N> {
    fun GetCvDto.request(): Kind<F, Result<ExampleResponse>> =
        call(::requestGet)

    fun RolesDto.request(): Kind<F, Result<List<RoleProfileResponse>>> =
        call(::requestRoles)

    fun ProficiencyDto.request(): Kind<F, Result<List<ProficiencyResponse>>> =
        call(::requestProficiency)

}

typealias Request <A, R> = (A, (Throwable) -> Unit, (R) -> Unit) -> Unit