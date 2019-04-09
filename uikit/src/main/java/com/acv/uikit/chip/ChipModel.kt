package com.acv.uikit.chip

import arrow.core.None
import arrow.core.Option

data class ChipModel(
        val id: String,
        val title: String,
        val closelable: Option<Closelable> = None,
        val checable: Option<Checable> = None
)