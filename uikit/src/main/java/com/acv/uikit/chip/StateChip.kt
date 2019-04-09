package com.acv.uikit.chip

sealed class StateChip
data class Checable(val isSelected: Boolean = false, val f: (Int) -> Unit) : StateChip()
data class Closelable(val f: (Int) -> Unit) : StateChip()
