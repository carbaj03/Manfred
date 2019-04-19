package com.acv.uikit.popup

import android.view.View
import com.acv.uikit.common.Model
import com.acv.uikit.icon.IconStyle

data class PopupModel(
    val view : View,
    val list: List<PopupAdapter>,
    val onClick: (PopupAdapter) -> Unit
) : Model