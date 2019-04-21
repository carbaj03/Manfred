package com.acv.manfred.curriculum.data.example

import java.util.HashMap

enum class RoleProfile private constructor(private val value: String) {

    PROJECT_MANAGER("project manager"),
    PRODUCT_MANAGER("product manager"),
    OPERATIONAL_MANAGER("operational manager"),
    ANALYST("analyst"),
    BUSINESS_ANALYST("business analyst"),
    QA_MANAGER("qa manager"),
    SOFTWARE_ARCHITECT("software architect"),
    PROCESS_ANALYST("process analyst"),
    TEST_ENGINEER("test engineer"),
    TEST_ANALYST("test analyst"),
    DATABASE_ADMINISTRATOR("database administrator"),
    DEVELOPER("developer"),
    SOFTWARE_ENGINEER("software engineer"),
    PRODUCT_OWNER("product owner"),
    SCRUM_MASTER("scrum master"),
    TEAM_LEAD("team lead"),
    UX_DESIGNER("UX designer"),
    UI_DESIGNER("UI designer");

    override fun toString(): String {
        return this.value
    }

    fun value(): String {
        return this.value
    }

    companion object {
        private val CONSTANTS = HashMap<String, RoleProfile>()

        init {
            for (c in values()) {
                CONSTANTS[c.value] = c
            }
        }

        fun fromValue(value: String): RoleProfile {
            val constant = CONSTANTS[value]
            return constant ?: throw IllegalArgumentException(value)
        }
    }

    fun
            toDomain() = this
}
