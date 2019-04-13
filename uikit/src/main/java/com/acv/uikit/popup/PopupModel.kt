package com.acv.uikit.popup

import com.acv.uikit.common.Model
import com.acv.uikit.icon.IconStyle

data class PopupModel(
    val icon: IconStyle,
    val title: Int,
    val onClick: () -> Unit
) : Model