package com.acv.manfred.curriculum.presentation.form.component.language

import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.form.components.common.ComponentContainer
import com.acv.manfred.curriculum.ui.form.components.language.LanguageView
import com.acv.manfred.curriculum.ui.form.languaje.LanguageViewModel

interface LanguageContainer :
    ComponentContainer<LanguageModel, LanguageDefault, LanguageComponentResponse, LanguageView>,
    Observable<LanguageViewModel>