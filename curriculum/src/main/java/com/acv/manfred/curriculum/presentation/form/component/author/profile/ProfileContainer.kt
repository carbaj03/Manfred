package com.acv.manfred.curriculum.presentation.form.component.author.profile

import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.form.components.author.profile.ProfileView
import com.acv.manfred.curriculum.ui.form.components.author.profile.ProfileViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentContainerK
import com.acv.uikit.adapterModel.AndroidDiffer
import com.acv.uikit.adapterModel.AndroidUpdatable

interface ProfileContainer :
    ComponentContainerK<AndroidDiffer, AndroidUpdatable, ProfileModel, ProfileDefault, ProfileComponentResponse, ProfileView>,
    Observable<ProfileViewModel>