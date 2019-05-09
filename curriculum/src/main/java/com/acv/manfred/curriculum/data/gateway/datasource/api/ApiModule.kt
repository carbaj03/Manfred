package com.acv.manfred.curriculum.data.gateway.datasource.api

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.*
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ProficiencyResponse.*
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse.*
import com.acv.manfred.curriculum.domain.GetCvDto
import com.acv.manfred.curriculum.domain.ProficiencyDto
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.RolesDto
import com.acv.manfred.curriculum.domain.model.ApiError
import com.google.gson.Gson
import com.google.gson.JsonParseException


class ApiModule {

    companion object

    fun requestGet(
        getBookingDto: GetCvDto,
        error: (Throwable) -> Unit,
        success: (ResultK<ExampleResponse>) -> Unit
    ): Unit =
        try {
            val gson = Gson()
//            val staff = gson.fromJson(FileReader("D:\\file.json"), Example::class.java)
            val staff = mockExample()
            success(staff.right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun requestRoles(
        roles: RolesDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<RoleProfileResponse>>) -> Unit
    ): Unit =
        try {
            success(mockRoles().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

    fun requestProficiency(
        proficiency: ProficiencyDto,
        error: (Throwable) -> Unit,
        success: (ResultK<List<ProficiencyResponse>>) -> Unit
    ): Unit =
        try {
            success(mockProficiency().right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }

}

typealias Role = String

private fun mockRoles(): List<RoleProfileResponse> =
    listOf(
        PROJECT_MANAGER,
        PRODUCT_MANAGER,
        OPERATIONAL_MANAGER,
        ANALYST,
        BUSINESS_ANALYST,
        QA_MANAGER,
        SOFTWARE_ARCHITECT,
        PROCESS_ANALYST,
        TEST_ENGINEER,
        TEST_ANALYST,
        DATABASE_ADMINISTRATOR,
        DEVELOPER,
        SOFTWARE_ENGINEER,
        PRODUCT_OWNER,
        SCRUM_MASTER,
        TEAM_LEAD,
        UX_DESIGNER,
        UI_DESIGNER
    )

private fun mockProficiency(): List<ProficiencyResponse> =
    listOf(
        ELEMENTARY,
        LIMITED_WORKING,
        PROFESSIONAL_WORKING,
        FULL_PROFESSIONAL,
        NATIVE_OR_BILINGUAL
    )

private fun mockExample(): ExampleResponse {
    return ExampleResponse(
        author = AuthorResponse(
            profile = ProfileResponse(
                name = "ACV",
                birthday = "",
                image = null,
                publicLinks = null,
                roles = emptySet(),
                yearsOfExperience = 2f
            ),
            intro = "La intro",
            professionalGoals = emptySet(),
            significantExperience = emptySet(),
            significantRelationships = emptySet(),
            transportableSkills = emptySet()
        ),
        education = setOf(),
        experience = setOf(),
        languages = setOf(),
        miscEducation = setOf(),
        questionnaire = setOf()
    )
}