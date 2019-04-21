package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.extension
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.data.example.Proficiency
import com.acv.manfred.curriculum.data.example.RoleProfile
import com.acv.manfred.curriculum.data.gateway.datasource.ApiModule
import com.acv.manfred.curriculum.domain.GetCvDto
import com.acv.manfred.curriculum.domain.ProficiencyDto
import com.acv.manfred.curriculum.domain.Result
import com.acv.manfred.curriculum.domain.RolesDto


@extension
interface NetworkModuleNetworkFetcher : NetworkFetcher<ApiModule> {
    companion object {
        private val apiModule = ApiModule()
    }

    override fun requestGet(getBookingDto: GetCvDto, error: (Throwable) -> Unit, success: (Result<Example>) -> Unit) =
        apiModule.requestGet(getBookingDto, error, success)

    override fun requestRoles(roles: RolesDto, error: (Throwable) -> Unit, success: (Result<List<RoleProfile>>) -> Unit) =
        apiModule.requestRoles(roles, error, success)

    override fun requestProficiency(proficiency: ProficiencyDto, error: (Throwable) -> Unit, success: (Result<List<Proficiency>>) -> Unit) =
        apiModule.requestProficiency(proficiency, error, success)
}

fun ApiModule.Companion.networkFetcher(): NetworkFetcher<ApiModule> =
    object : NetworkModuleNetworkFetcher {}

interface NetworkFetcher<N> {
    fun requestGet(
        getBookingDto: GetCvDto,
        error: (Throwable) -> Unit,
        success: (Result<Example>) -> Unit
    ): Unit

    fun requestRoles(
        roles: RolesDto,
        error: (Throwable) -> Unit,
        success: (Result<List<RoleProfile>>) -> Unit
    ): Unit

    fun requestProficiency(
        proficiency: ProficiencyDto,
        error: (Throwable) -> Unit,
        success: (Result<List<Proficiency>>) -> Unit
    ): Unit
}

interface NetworkOperations<F, N> : CallAsync<F>, NetworkFetcher<N> {
    fun GetCvDto.request(): Kind<F, Result<Example>> =
        call(::requestGet)

    fun RolesDto.request(): Kind<F, Result<List<RoleProfile>>> =
        call(::requestRoles)

    fun ProficiencyDto.request(): Kind<F, Result<List<Proficiency>>> =
        call(::requestProficiency)

}

typealias Request <A, R> = (A, (Throwable) -> Unit, (R) -> Unit) -> Unit