package com.acv.uikit.dimens

import androidx.annotation.DimenRes
import com.acv.uikit.R

sealed class DimensIcon(@DimenRes val size: Int) : Dimens

object Icon24 : DimensIcon(R.dimen.icon_size_24)
object Icon32 : DimensIcon(R.dimen.icon_size_32)