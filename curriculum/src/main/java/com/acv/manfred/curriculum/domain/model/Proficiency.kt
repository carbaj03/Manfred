package com.acv.manfred.curriculum.domain.model


sealed class Proficiency(
    val id: GenerateId,
    val value: String
) {
    companion object {
        operator fun invoke(value: String): Proficiency =
            when (value) {
                Elementary.value -> Elementary
                LimitedWorking.value -> LimitedWorking
                ProfessionalWorking.value -> ProfessionalWorking
                FullProfessional.value -> FullProfessional
                NativeOrBilingual.value -> NativeOrBilingual
                else -> Empty
            }
    }

}

object Elementary : Proficiency(WithId("1"), "elementary")
object LimitedWorking : Proficiency(WithId("2"), "limited working")
object ProfessionalWorking : Proficiency(WithId("3"), "professional working")
object FullProfessional : Proficiency(WithId("4"), "full professional")
object NativeOrBilingual : Proficiency(WithId("5"), "native or bilingual")

object Empty : Proficiency(WithId("0"), "empty")
