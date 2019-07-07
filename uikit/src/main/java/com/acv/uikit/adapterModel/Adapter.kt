package com.acv.uikit.adapterModel

interface Adapter<M> {
    fun swap(newItems: List<M>, compare: (M, M) -> Boolean = { x, y -> x == y })
    fun set(position: POSITION, item: M)
}
//
//interface AdapterK<A : Differ, B : Updatable> {
//
//    fun swip(asyncDiffResult: AsyncDiffResult<A, B>): Unit =
//        asyncDiffResult.update()
//}

typealias POSITION = Int

