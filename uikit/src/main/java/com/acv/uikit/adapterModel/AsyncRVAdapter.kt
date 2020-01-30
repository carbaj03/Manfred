package com.acv.uikit.adapterModel

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.acv.uikit.common.inflate


data class RvState<A>(
    val items: MutableList<A> = mutableListOf(),
    val factory: (A, View) -> RvHolder<A>
) {
    private val mapper: HashMap<Layout, Class<out A>> = hashMapOf()
}

sealed class Differ

data class AndroidDiffer(
    val diff: DiffUtil.DiffResult
) : Differ()



sealed class Updatable {
//    fun onInserted(position: Int, count: Int)
//
//    fun onRemoved(position: Int, count: Int)
//
//    fun onMoved(fromPosition: Int, toPosition: Int)
//
//    fun onChanged(position: Int, count: Int, payload: Any?)
}

data class AndroidUpdatable(
    val callback: ListUpdateCallback
) : Updatable()


//////


interface AsyncDiffResult<A : Differ, B : Updatable> {
    fun update(differ : A, updatable : B): Unit
}

interface AndroidDiffResult : AsyncDiffResult<AndroidDiffer, AndroidUpdatable> {
//    override val differ : AndroidDiffer
//    override val updatable: AndroidUpdatable

    override fun update(differ : AndroidDiffer, updatable: AndroidUpdatable): Unit =
        differ.diff.dispatchUpdatesTo(updatable.callback)
}


///////

interface RvHolder<in A> {
    fun bindTo(model: A)
}

interface Inflater<A> {
    val viewType: Int


    fun inflate(): ResultInflate<A>
}

data class ToInflate(
    val parent: ViewGroup, override val viewType: Layout
) : Inflater<View> {
    override fun inflate() =
        ResultInflateView(parent.inflate(viewType))

}

interface ResultInflate<A> {
    val result: A
}

data class ResultInflateView(
    override val result: View
) : ResultInflate<View>

data class RvCallback<A, V>(
    val create: (Inflater<V>) -> RvHolder<A>,
    val bind: (RvHolder<A>, Int) -> Unit,
    val itemView: (Int) -> Int,
    val count: () -> Int,
    val swap: () -> Unit
)

//class ExampleModel() : AdapterModel(1)


//class Example {
//
//
//    fun a() {
//        AsyncRVAdapter<ExampleModel>()
//    }
//
//    fun <A> RvState<A>.c() =
//        RvCallback<ExampleModel, View>(
//            create = { inflater -> factory(mapper[inflater.viewType]!!.newInstance(), inflater.inflate()) },
//            bind = { holder, position -> holder.bindTo(items[position]) },
//            itemView = { position ->
//                mapper[items[position].layout] = items[position]::class.java
//                return items[position].layout
//            },
//            count = { items.size },
//            swap = {
//                items.clear()
//                items.addAll(newItems)
//            }
//        )
//
//
//}
//
//
//class AsyncRVAdapter<A : AdapterModel>(
//    val callback: RvCallback<A, View>
//) : RecyclerView.Adapter<BaseViewHolder<A>>(), AsyncAdapter<AndroidDiffer, AndroidUpdatable> {
//
//    override fun swap(diffResult: AsyncDiffResult<AndroidDiffer, AndroidUpdatable>) {
//        callback.swap()
//        diffResult.update(AndroidUpdatable(AdapterListUpdateCallback(this)))
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Layout): BaseViewHolder<A> =
//        callback.create(ToInflate(parent, viewType)) as BaseViewHolder<A>
//
//    override fun onBindViewHolder(holder: BaseViewHolder<A>, position: Int): Unit =
//        callback.bind(holder, position)
//
//    override fun getItemViewType(position: Int): Int =
//        callback.itemView(position)
//
//    override fun getItemCount(): Int =
//        callback.count()
//}