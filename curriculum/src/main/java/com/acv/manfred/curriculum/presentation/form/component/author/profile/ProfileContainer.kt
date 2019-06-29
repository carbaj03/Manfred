package com.acv.manfred.curriculum.presentation.form.component.author.profile

import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.form.components.author.profile.ProfileView
import com.acv.manfred.curriculum.ui.form.components.author.profile.ProfileViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentContainer

interface ProfileContainer :
    ComponentContainer<ProfileModel, ProfileDefault, ProfileComponentResponse, ProfileView>,
    Observable<ProfileViewModel>