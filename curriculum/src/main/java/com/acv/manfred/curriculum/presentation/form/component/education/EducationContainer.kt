package com.acv.manfred.curriculum.presentation.form.component.education

import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.form.components.common.ComponentContainer
import com.acv.manfred.curriculum.ui.form.components.education.EducationView
import com.acv.manfred.curriculum.ui.form.components.education.EducationViewModel

interface EducationContainer :
    ComponentContainer<EducationModel, EducationDefault, EducationComponentResponse, EducationView>,
    Observable<EducationViewModel>