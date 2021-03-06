package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.Proficiency

enum class ProficiencyResponse constructor(private val value: String) {
    ELEMENTARY("elementary"),
    LIMITED_WORKING("limited working"),
    PROFESSIONAL_WORKING("professional working"),
    FULL_PROFESSIONAL("full professional"),
    NATIVE_OR_BILINGUAL("native or bilingual");

    override fun toString(): String {
        return this.value
    }

    fun value(): String {
        return this.value
    }

    companion object {
        private val CONSTANTS = HashMap<String, ProficiencyResponse>()

        init {
            for (c in values()) {
                CONSTANTS[c.value] = c
            }
        }

        fun fromValue(value: String): ProficiencyResponse {
            val constant = CONSTANTS[value]
            return constant ?: throw IllegalArgumentException(value)
        }
    }

    fun toDomain() = Proficiency(value)
}