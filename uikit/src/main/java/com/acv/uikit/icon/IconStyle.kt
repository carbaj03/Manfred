package com.acv.uikit.icon

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.acv.uikit.R
import com.acv.uikit.color.Color
import com.acv.uikit.color.Primary
import com.acv.uikit.dimens.DimensIcon
import com.acv.uikit.dimens.Icon24
import com.fintonic.uikit.common.Style

sealed class IconStyle(
    val color: Color,
    val resource: Int,
    val dimen: DimensIcon
) : Style {
    fun tintIcon(context: Context): Drawable =
        DrawableCompat.wrap(ContextCompat.getDrawable(context, resource)!!).apply {
            DrawableCompat.setTint(this, color.getColor(context))
            setBounds(dimen.size, dimen.size, dimen.size, dimen.size)
        }
}

object AddIcon : IconStyle(Primary, R.drawable.ic_add, Icon24)