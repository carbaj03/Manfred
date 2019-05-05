package com.acv.manfred.curriculum.ui.form.components.common

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData


typealias MediatorState = MediatorLiveData<ComponentValidation>

typealias ObservableValidation = MutableLiveData<ComponentValidation>

sealed class ComponentValidation
object Valid : ComponentValidation()
object Invalid : ComponentValidation()
data class Error(val msg: String) : ComponentValidation()


fun ObservableValidation.valid() {
    value = Valid
}

fun ObservableValidation.invalid() {
    value = Invalid
}

//interface a{
//    private val run: MediatorState = MediatorLiveData()
//
//    private fun List<Validable>.combine(): ComponentValidation =
//        firstOrNull { it.run.value is Valid }?.run?.value ?: defaultState()
//
//    private fun List<Validable>.defaultState(): ComponentValidation =
//        if (forAll { it.run.value == Valid }) Valid else Invalid
//
//    private val component: List<Validable>
//        get() {
//            val temp: MutableList<Validable> = mutableListOf()
//            container().forEachChild { temp.add(it as Validable) }
//            return temp
//        }
//
//    private fun container(): LinearLayout = (questionnaire_container as LinearLayout)
//
//    inline fun ViewGroup.forEachChild(action: (View) -> Unit) {
//        for (i in 0 until childCount) {
//            action(getChildAt(i))
//        }
//    }
//}

