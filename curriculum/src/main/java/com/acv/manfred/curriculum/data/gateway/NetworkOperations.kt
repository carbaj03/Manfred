package com.acv.manfred.curriculum.data.gateway

import android.content.Context
import arrow.Kind
import arrow.extension
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ProficiencyResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.data.gateway.datasource.local.AppDatabase
import com.acv.manfred.curriculum.data.gateway.datasource.local.DbModule
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.domain.*


//@extension
interface NetworkModuleNetworkFetcher : NetworkFetcher<ApiModule> {

    companion object {
        private val apiModule = ApiModule()

    }

    val dbModule: DbModule

    override fun requestGet(getBookingDto: GetCvDto, error: (Throwable) -> Unit, success: (Result<ExampleResponse>) -> Unit) =
        apiModule.requestGet(getBookingDto, error, success)

    override fun requestRoles(roles: RolesDto, error: (Throwable) -> Unit, success: (Result<List<RoleProfileResponse>>) -> Unit) =
        apiModule.requestRoles(roles, error, success)

    override fun requestProficiency(proficiency: ProficiencyDto, error: (Throwable) -> Unit, success: (Result<List<ProficiencyResponse>>) -> Unit) =
        apiModule.requestProficiency(proficiency, error, success)

    override fun save(proficiency: QuestionnaireDto, error: (Throwable) -> Unit, success: (Result<List<QuestionnaireEntity>>) -> Unit) =
        dbModule.save(proficiency, error, success)

    override fun all(proficiency: GetQuestionnaireDto, error: (Throwable) -> Unit, success: (Result<List<QuestionnaireEntity>>) -> Unit) =
        dbModule.all(proficiency, error, success)
}

fun ApiModule.Companion.networkFetcher(context: Context): NetworkFetcher<ApiModule> =
    object : NetworkModuleNetworkFetcher {
        override val dbModule: DbModule = DbModule(AppDatabase.getInstance(context.applicationContext).questionaireDao())
    }

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

    fun save(
        proficiency: QuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (Result<List<QuestionnaireEntity>>) -> Unit
    ): Unit

    fun all(
        proficiency: GetQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (Result<List<QuestionnaireEntity>>) -> Unit
    ): Unit

}

interface NetworkOperations<F, N> : CallAsync<F>, NetworkFetcher<N> {
    fun GetCvDto.request(): Kind<F, Result<ExampleResponse>> =
        call(::requestGet)

    fun RolesDto.request(): Kind<F, Result<List<RoleProfileResponse>>> =
        call(::requestRoles)

    fun ProficiencyDto.request(): Kind<F, Result<List<ProficiencyResponse>>> =
        call(::requestProficiency)

    fun QuestionnaireDto.persist(): Kind<F, Result<List<QuestionnaireEntity>>> =
        call(::save)

    fun GetQuestionnaireDto.request(): Kind<F, Result<List<QuestionnaireEntity>>> =
        call(::all)
}