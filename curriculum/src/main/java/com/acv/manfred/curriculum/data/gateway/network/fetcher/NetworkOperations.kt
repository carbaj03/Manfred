package com.acv.manfred.curriculum.data.gateway.network.fetcher

import android.content.Context
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.data.gateway.datasource.local.AppDatabase
import com.acv.manfred.curriculum.data.gateway.datasource.local.db.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.*
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.gategay.ResultK


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


interface NetworkEducationModuleNetworkFetcher : NetworkEducationFetcher<ApiModule> {
    companion object {
        private val apiModule = ApiModule()
    }

    val dbModule: DbEducationModule

    override suspend fun EducationDto.saveEntity(): ResultK<List<EducationEntity>> =
        dbModule.save(this)

    override suspend fun RemoveEducationDto.removeEntity(): ResultK<List<EducationEntity>> =
        dbModule.remove(this)

    override suspend fun  GetEducationDto.allEntity(): ResultK<List<EducationEntity>> =
        dbModule.all(this)

    override suspend fun AddEducationDto.addEntity(): ResultK<List<EducationEntity>> =
        dbModule.add(this)
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

fun ApiModule.Companion.networkEducationFetcher(context: Context): NetworkEducationFetcher<ApiModule> =
    object : NetworkEducationModuleNetworkFetcher {
        override val dbModule: DbEducationModule =
            DbEducationModule(AppDatabase.getInstance(context.applicationContext).educationDao())
    }