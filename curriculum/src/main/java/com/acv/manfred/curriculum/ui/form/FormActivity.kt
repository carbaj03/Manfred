package com.acv.manfred.curriculum.ui.form

import arrow.core.None
import arrow.core.some
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.SavedInstance
import com.acv.uikit.chip.ChipModel
import com.acv.uikit.chip.Closelable
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : BaseActivity() {

    //    val rvadapterModel by lazy {
    //        RVAdapter<MediaAdapter> { video, v ->
    //            when (video) {
    //                is VideoAdapter -> VideoAdapterHolder(v)
    //                is AudioAdapter -> AudioAdapterHolder(v)
    //            }
    //        }
    //    }

    var temp = listOf(ChipModel("0", "asdfsa", None, None))

    override fun getLayout(): Int = R.layout.activity_form

    override fun create(savedInstance: SavedInstance) {
        //        rvVideo.layoutManager = LinearLayoutManager(this)
        //        rvVideo.adapter = rvadapterModel
        chaCategory.swap(temp) { a, b -> a.id == b.id }
        chaCategory.action { addCategory() }
    }

    private fun addCategory() {
        temp = temp.plus(ChipModel(temp.size.toString(), "other", Closelable { removeCategory(it) }.some(), None))
        chaCategory.swap(temp) { a, b -> a.id == b.id }
    }

    private fun removeCategory(it: Int) {
        temp = temp.minus(temp[it])
        chaCategory.swap(temp) { a, b -> a.id == b.id }
    }

}