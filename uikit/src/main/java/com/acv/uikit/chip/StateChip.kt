package com.acv.uikit.chip

sealed class StateChip
data class Checable(val isSelected: Boolean = false, val f: (ChipModel) -> Unit) : StateChip()
data class Closelable(val f: (ChipModel) -> Unit) : StateChip()
