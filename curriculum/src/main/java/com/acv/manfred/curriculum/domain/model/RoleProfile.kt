package com.acv.manfred.curriculum.domain.model


sealed class RoleProfile(val value: String) {
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

object PROJECT_MANAGER : RoleProfile("project manager")
object PRODUCT_MANAGER : RoleProfile("product manager")
object OPERATIONAL_MANAGER : RoleProfile("operational manager")
object ANALYST : RoleProfile("analyst")
object BUSINESS_ANALYST : RoleProfile("business analyst")
object QA_MANAGER : RoleProfile("qa manager")
object SOFTWARE_ARCHITECT : RoleProfile("software architect")
object PROCESS_ANALYST : RoleProfile("process analyst")
object TEST_ENGINEER : RoleProfile("test engineer")
object TEST_ANALYST : RoleProfile("test analyst")
object DATABASE_ADMINISTRATOR : RoleProfile("database administrator")
object DEVELOPER : RoleProfile("developer")
object SOFTWARE_ENGINEER : RoleProfile("software engineer")
object PRODUCT_OWNER : RoleProfile("product owner")
object SCRUM_MASTER : RoleProfile("scrum master")
object TEAM_LEAD : RoleProfile("team lead")
object UX_DESIGNER : RoleProfile("UX designer")
object UI_DESIGNER : RoleProfile("UI designer")
