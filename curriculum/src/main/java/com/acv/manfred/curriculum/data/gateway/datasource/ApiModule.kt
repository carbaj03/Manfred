package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.extension
import com.acv.manfred.curriculum.data.example.*
import com.acv.manfred.curriculum.data.example.Proficiency.*
import com.acv.manfred.curriculum.data.example.RoleProfile.*
import com.acv.manfred.curriculum.data.gateway.NetworkFetcher
import com.acv.manfred.curriculum.domain.GetCvDto
import com.acv.manfred.curriculum.domain.ProficiencyDto
import com.acv.manfred.curriculum.domain.Result
import com.acv.manfred.curriculum.domain.RolesDto
import com.acv.manfred.curriculum.domain.model.ApiError
import com.google.gson.Gson
import com.google.gson.JsonParseException
import java.io.FileReader


class ApiModule {

    companion object

    fun requestGet(
        getBookingDto: GetCvDto,
        error: (Throwable) -> Unit,
        success: (Result<Example>) -> Unit
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
        success: (Result<List<RoleProfile>>) -> Unit
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
        success: (Result<List<Proficiency>>) -> Unit
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

private fun mockRoles(): List<RoleProfile> =
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

private fun mockProficiency(): List<Proficiency> =
    listOf(
        ELEMENTARY,
        LIMITED_WORKING,
        PROFESSIONAL_WORKING,
        FULL_PROFESSIONAL,
        NATIVE_OR_BILINGUAL
    )

private fun mockExample(): Example {
    return Example(
        author = Author(
            profile = Profile(
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