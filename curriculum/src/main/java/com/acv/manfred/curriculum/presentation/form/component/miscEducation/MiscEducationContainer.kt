package com.acv.manfred.curriculum.presentation.form.component.miscEducation

import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.form.MiscEducationViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentContainer
import com.acv.manfred.curriculum.ui.form.components.miscEducation.MiscEducationComponent

interface MiscEducationContainer :
    ComponentContainer<MiscEducationModel, MiscEducationComponent>,
    Observable<MiscEducationViewModel>