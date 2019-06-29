package com.acv.manfred.curriculum.domain.model


sealed class RoleProfile(
    val id: GenerateId,
    val value: String
) {
    companion object {
        operator fun invoke(value: String): RoleProfile =
            when (value) {
                PROJECT_MANAGER.value -> PROJECT_MANAGER
                PRODUCT_MANAGER.value -> PRODUCT_MANAGER
                OPERATIONAL_MANAGER.value -> OPERATIONAL_MANAGER
                ANALYST.value -> ANALYST
                BUSINESS_ANALYST.value -> BUSINESS_ANALYST
                QA_MANAGER.value -> QA_MANAGER
                SOFTWARE_ARCHITECT.value -> SOFTWARE_ARCHITECT
                PROCESS_ANALYST.value -> PROCESS_ANALYST
                TEST_ENGINEER.value -> TEST_ENGINEER
                TEST_ANALYST.value -> TEST_ANALYST
                DATABASE_ADMINISTRATOR.value -> DATABASE_ADMINISTRATOR
                DEVELOPER.value -> DEVELOPER
                SOFTWARE_ENGINEER.value -> SOFTWARE_ENGINEER
                PRODUCT_OWNER.value -> PRODUCT_OWNER
                SCRUM_MASTER.value -> SCRUM_MASTER
                TEAM_LEAD.value -> TEAM_LEAD
                UX_DESIGNER.value -> UX_DESIGNER
                UI_DESIGNER.value -> UI_DESIGNER
                else -> PRODUCT_OWNER
            }
    }
}

object PROJECT_MANAGER : RoleProfile(WithId("1"),"project manager")
object PRODUCT_MANAGER : RoleProfile(WithId("2"),"product manager")
object OPERATIONAL_MANAGER : RoleProfile(WithId("3"),"operational manager")
object ANALYST : RoleProfile(WithId("4"),"analyst")
object BUSINESS_ANALYST : RoleProfile(WithId("5"),"business analyst")
object QA_MANAGER : RoleProfile(WithId("6"),"qa manager")
object SOFTWARE_ARCHITECT : RoleProfile(WithId("7"),"software architect")
object PROCESS_ANALYST : RoleProfile(WithId("8"),"process analyst")
object TEST_ENGINEER : RoleProfile(WithId("9"),"test engineer")
object TEST_ANALYST : RoleProfile(WithId("10"),"test analyst")
object DATABASE_ADMINISTRATOR : RoleProfile(WithId("11"),"database administrator")
object DEVELOPER : RoleProfile(WithId("12"),"developer")
object SOFTWARE_ENGINEER : RoleProfile(WithId("13"),"software engineer")
object PRODUCT_OWNER : RoleProfile(WithId("14"),"product owner")
object SCRUM_MASTER : RoleProfile(WithId("15"),"scrum master")
object TEAM_LEAD : RoleProfile(WithId("16"),"team lead")
object UX_DESIGNER : RoleProfile(WithId("17"),"UX designer")
object UI_DESIGNER : RoleProfile(WithId("18"),"UI designer")
