package com.acv.manfred.curriculum.domain.model


sealed class Proficiency(val value: String){
    companion object{
        operator fun invoke(value : String) =
                when(value){
                    Elementary.value -> Elementary
                    LimitedWorking .value ->  LimitedWorking
                    ProfessionalWorking.value -> ProfessionalWorking
                    FullProfessional.value -> FullProfessional
                    NativeOrBilingual.value -> NativeOrBilingual
                    else -> Elementary
                }
    }
}
object Elementary : Proficiency("elementary")
object LimitedWorking : Proficiency("limited working")
object ProfessionalWorking : Proficiency("professional working")
object FullProfessional: Proficiency("full professional")
object NativeOrBilingual: Proficiency("native or bilingual")
