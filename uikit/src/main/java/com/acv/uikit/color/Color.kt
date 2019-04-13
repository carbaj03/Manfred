package com.acv.uikit.color

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.acv.uikit.R

sealed class Color(
		val name: String,
		@ColorRes val color: Int
) {
	fun getColor(context: Context) =
			ContextCompat.getColor(context, color)
}

object Primary : Color("fucsia", R.color.primaryColor)

object PrimaryLight : Color("blue", R.color.primaryLightColor)
object PrimaryDarkColor : Color("gum", R.color.primaryDarkColor)
object SecondaryColor : Color("navy", R.color.secondaryColor)
object SecondaryLightColor : Color("yellow", R.color.secondaryLightColor)
object SecondaryDarkColor : Color("aquamarine", R.color.secondaryDarkColor)
object PrimaryTextColor : Color("pink", R.color.primaryTextColor)
object SecondaryTextColor : Color("baby_blue", R.color.secondaryTextColor)
object Background : Color("white", R.color.background)