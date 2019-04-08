package com.acv.manfred.curriculum.ui.form

import androidx.recyclerview.widget.LinearLayoutManager
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.SavedInstance
import com.acv.uikit.adapterRender.RVAdapter
import com.acv.uikit.adapterRender.ViewHolder
import com.acv.uikit.renderer.RVRendererAdapter
import com.acv.uikit.renderer.Renderer
import com.acv.uikit.renderer.RendererBuilder
import kotlinx.android.synthetic.main.activity_form.*
import java.util.*

class FormActivity : BaseActivity() {

    //    private var adapter: RVRendererAdapter<Video>? = null
    val rvadapter by lazy {
        RVAdapter<Media>(
            l = { video, v ->
                when (video) {
                    is Video -> VideoHolder(v)
                    is Audio -> AudioHolder(v)
                }
            },
            f = hashMapOf(
                Video::class.java to VideoHolder::class.java,
                Audio::class.java to AudioHolder::class.java
            )
        )
    }

    override fun getLayout(): Int = R.layout.activity_form

    override fun create(savedInstance: SavedInstance) {
        rvVideo.layoutManager = LinearLayoutManager(this)
        rvVideo.adapter = rvadapter
        val randomVideoCollectionGenerator = RandomVideoCollectionGenerator()
        rvadapter.swap(randomVideoCollectionGenerator.generate(100).videos)
    }


//    private fun initAdapter() {
//        val randomVideoCollectionGenerator = RandomVideoCollectionGenerator()
//        val videoCollection = randomVideoCollectionGenerator.generateListAdapteeVideoCollection(100)
//
//        val rendererBuilder = RendererBuilder<Video>()
//            .withPrototype(
//                RemovableVideoRenderer(object : RemovableVideoRenderer.Listener {
//                    override fun onRemoveButtonTapped(video: Video) {
//                        val clonedList = ArrayList(videoCollection as Collection<Video>)
//                        clonedList.remove(video)
//                        adapter!!.diffUpdate(clonedList)
//                    }
//                })
//            )
//            .bind(Video::class.java, RemovableVideoRenderer::class.java as Class<Renderer<Video>>)
//
//        adapter = RVRendererAdapter(rendererBuilder, videoCollection)
//    }


}