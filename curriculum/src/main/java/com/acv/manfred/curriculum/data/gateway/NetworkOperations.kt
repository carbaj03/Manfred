package com.acv.manfred.curriculum.data.gateway

import android.content.Context
import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ProficiencyResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.data.gateway.datasource.local.AppDatabase
import com.acv.manfred.curriculum.data.gateway.datasource.local.db.DbLanguageModule
import com.acv.manfred.curriculum.data.gateway.datasource.local.db.DbMiscEducationModule
import com.acv.manfred.curriculum.data.gateway.datasource.local.db.DbProficiencyModule
import com.acv.manfred.curriculum.data.gateway.datasource.local.db.DbQuestionnaireModule
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.LanguageEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.MiscEducationEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProficiencyEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.*


//@extension
interface NetworkModuleNetworkFetcher : NetworkFetcher<ApiModule> {
    companion object {
        private val apiModule = ApiModule()
    }

    val dbModule: DbQuestionnaireModule

    override fun requestGet(getBookingDto: GetCvDto, error: (Throwable) -> Unit, success: (ResultK<ExampleResponse>) -> Unit) =
        apiModule.requestGet(getBookingDto, error, success)

    override fun requestRoles(roles: RolesDto, error: (Throwable) -> Unit, success: (ResultK<List<RoleProfileResponse>>) -> Unit) =
        apiModule.requestRoles(roles, error, success)
}

//@extension
interface NetworkQuestionnaireModuleNetworkFetcher : NetworkQuestionnaireFetcher<ApiModule> {
    companion object {
        private val apiModule = ApiModule()
    }

    val dbModule: DbQuestionnaireModule

    override fun save(proficiency: QuestionnaireDto, error: (Throwable) -> Unit, success: (ResultK<List<QuestionnaireEntity>>) -> Unit) =
        dbModule.save(proficiency, error, success)

    override fun remove(proficiency: RemoveQuestionnaireDto, error: (Throwable) -> Unit, success: (ResultK<List<QuestionnaireEntity>>) -> Unit) =
        dbModule.remove(proficiency, error, success)

    override fun all(proficiency: GetQuestionnaireDto, error: (Throwable) -> Unit, success: (ResultK<List<QuestionnaireEntity>>) -> Unit) =
        dbModule.all(proficiency, error, success)

    override fun add(questionnaire: AddQuestionnaireDto, error: (Throwable) -> Unit, success: (ResultK<List<QuestionnaireEntity>>) -> Unit) =
        dbModule.add(questionnaire, error, success)
}

interface NetworkMiscEducationModuleNetworkFetcher : NetworkMiscEducationFetcher<ApiModule> {
    companion object {
        private val apiModule = ApiModule()
    }

    val dbModule: DbMiscEducationModule

    override fun save(dto: MiscEducationDto, error: (Throwable) -> Unit, success: (ResultK<List<MiscEducationEntity>>) -> Unit) =
        dbModule.save(dto, error, success)

    override fun remove(dto: RemoveMiscEducationDto, error: (Throwable) -> Unit, success: (ResultK<List<MiscEducationEntity>>) -> Unit) =
        dbModule.remove(dto, error, success)

    override fun all(dto: GetMiscEducationDto, error: (Throwable) -> Unit, success: (ResultK<List<MiscEducationEntity>>) -> Unit) =
        dbModule.all(dto, error, success)

    override fun add(dto: AddMiscEducationDto, error: (Throwable) -> Unit, success: (ResultK<List<MiscEducationEntity>>) -> Unit) =
        dbModule.add(dto, error, success)
}

interface NetworkLanguageModuleNetworkFetcher : NetworkLanguageFetcher<ApiModule> {
    companion object {
        private val apiModule = ApiModule()
    }

    val dbModule: DbLanguageModule

    override fun save(dto: LanguageDto, error: (Throwable) -> Unit, success: (ResultK<List<LanguageEntity>>) -> Unit) =
        dbModule.save(dto, error, success)

    override fun remove(dto: RemoveLanguageDto, error: (Throwable) -> Unit, success: (ResultK<List<LanguageEntity>>) -> Unit) =
        dbModule.remove(dto, error, success)

    override fun all(dto: GetLanguageDto, error: (Throwable) -> Unit, success: (ResultK<List<LanguageEntity>>) -> Unit) =
        dbModule.all(dto, error, success)

    override fun add(dto: AddLanguageDto, error: (Throwable) -> Unit, success: (ResultK<List<LanguageEntity>>) -> Unit) =
        dbModule.add(dto, error, success)
}

interface NetworkProficiencyModuleNetworkFetcher : NetworkProficiencyFetcher<ApiModule> {
    companion object {
        private val apiModule = ApiModule()
    }

    val dbModule: DbProficiencyModule

    override fun all(proficiency: GetProficiencyDto, error: (Throwable) -> Unit, success: (ResultK<List<ProficiencyEntity>>) -> Unit) =
        dbModule.all(proficiency, error, success)

}

fun ApiModule.Companion.networkLanguageFetcher(context: Context): NetworkLanguageFetcher<ApiModule> =
    object : NetworkLanguageModuleNetworkFetcher {
        override val dbModule: DbLanguageModule =
            DbLanguageModule(AppDatabase.getInstance(context.applicationContext).languageDao())
    }

fun ApiModule.Companion.networkProficiencyFetcher(context: Context): NetworkProficiencyFetcher<ApiModule> =
    object : NetworkProficiencyModuleNetworkFetcher {
        override val dbModule: DbProficiencyModule =
            DbProficiencyModule(AppDatabase.getInstance(context.applicationContext).proficiencyDao())
    }

fun ApiModule.Companion.networkFetcher(context: Context): NetworkFetcher<ApiModule> =
    object : NetworkModuleNetworkFetcher {
        override val dbModule: DbQuestionnaireModule =
            DbQuestionnaireModule(AppDatabase.getInstance(context.applicationContext).questionaireDao())
    }

fun ApiModule.Companion.networkQuestionnaireFetcher(context: Context): NetworkQuestionnaireFetcher<ApiModule> =
    object : NetworkQuestionnaireModuleNetworkFetcher {
        override val dbModule: DbQuestionnaireModule =
            DbQuestionnaireModule(AppDatabase.getInstance(context.applicationContext).questionaireDao())
    }

fun ApiModule.Companion.networkMiscEducationFetcher(context: Context): NetworkMiscEducationFetcher<ApiModule> =
    object : NetworkMiscEducationModuleNetworkFetcher {
        override val dbModule: DbMiscEducationModule =
            DbMiscEducationModule(AppDatabase.getInstance(context.applicationContext).miscEducationDao())
    }

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

interface NetworkQuestionnaireFetcher<N> {
    fun save(
        proficiency: QuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit

    fun add(
        questionnaire: AddQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit

    fun remove(
        questionnaireDto: RemoveQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit


    fun all(
        proficiency: GetQuestionnaireDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<QuestionnaireEntity>>) -> Unit
    ): Unit

}

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

interface NetworkLanguageFetcher<N> {
    fun save(
        proficiency: LanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit

    fun add(
        questionnaire: AddLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit

    fun remove(
        questionnaireDto: RemoveLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit


    fun all(
        proficiency: GetLanguageDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<LanguageEntity>>) -> Unit
    ): Unit

}

interface NetworkProficiencyFetcher<N> {

    fun all(
        proficiency: GetProficiencyDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<ProficiencyEntity>>) -> Unit
    ): Unit

}

interface NetworkOperations<F, N> : CallAsync<F>, NetworkFetcher<N> {
    fun GetCvDto.request(): Kind<F, ResultK<ExampleResponse>> =
        call(::requestGet)

    fun RolesDto.request(): Kind<F, ResultK<List<RoleProfileResponse>>> =
        call(::requestRoles)
}

interface NetworkQuestionnaireOperations<F, N> : CallAsync<F>, NetworkQuestionnaireFetcher<N> {
    fun QuestionnaireDto.persist(): Kind<F, ResultK<List<QuestionnaireEntity>>> =
        call(::save)

    fun AddQuestionnaireDto.persist(): Kind<F, ResultK<List<QuestionnaireEntity>>> =
        call(::add)

    fun RemoveQuestionnaireDto.delete(): Kind<F, ResultK<List<QuestionnaireEntity>>> =
        call(::remove)

    fun GetQuestionnaireDto.request(): Kind<F, ResultK<List<QuestionnaireEntity>>> =
        call(::all)
}

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

interface NetworkLanguageOperations<F, N> : CallAsync<F>, NetworkLanguageFetcher<N> {
    fun LanguageDto.persist(): Kind<F, ResultK<List<LanguageEntity>>> =
        call(::save)

    fun AddLanguageDto.persist(): Kind<F, ResultK<List<LanguageEntity>>> =
        call(::add)

    fun RemoveLanguageDto.delete(): Kind<F, ResultK<List<LanguageEntity>>> =
        call(::remove)

    fun GetLanguageDto.request(): Kind<F, ResultK<List<LanguageEntity>>> =
        call(::all)
}

interface NetworkProficiencyOperations<F, N> : CallAsync<F>, NetworkProficiencyFetcher<N> {

    fun GetProficiencyDto.request(): Kind<F, ResultK<List<ProficiencyEntity>>> =
        call(::all)
}