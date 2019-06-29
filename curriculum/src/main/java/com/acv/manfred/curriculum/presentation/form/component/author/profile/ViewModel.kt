package com.acv.manfred.curriculum.presentation.form.component.author.profile

import com.acv.manfred.curriculum.presentation.form.component.common.ComponentModel

data class ViewModel<A : ComponentModel> (val models : List<A>)