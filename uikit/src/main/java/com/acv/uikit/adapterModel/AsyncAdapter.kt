package com.acv.uikit.adapterModel


interface AsyncAdapter<A : Differ, B : Updatable> {
    fun swap(diffResult: AsyncDiffResult<A, B>)
}
