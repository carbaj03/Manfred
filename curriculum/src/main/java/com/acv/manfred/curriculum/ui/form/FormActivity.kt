package com.acv.manfred.curriculum.ui.form

import androidx.recyclerview.widget.LinearLayoutManager
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.SavedInstance
import com.acv.uikit.adapterModel.RVAdapter
import com.acv.uikit.adapterRender.RVAdapter as renderAdapter
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : BaseActivity() {

    //    private var adapter: RVRendererAdapter<Video>? = null
    val rvadapter by lazy {
        renderAdapter(
            l = { video, v ->
                when (video) {
                    is Video -> VideoHolder(v)
                    is Audio -> AudioHolder(v)
                }
            },
            f = hashMapOf(
                Video::class.java to R.layout.video_renderer,
                Audio::class.java to R.layout.audio_renderer
            )
        )
    }

    val rvadapterModel by lazy {
        RVAdapter<MediaAdapter> { video, v ->
            when (video) {
                is VideoAdapter -> VideoAdapterHolder(v)
                is AudioAdapter -> AudioAdapterHolder(v)
            }
        }
    }

    override fun getLayout(): Int = R.layout.activity_form

    override fun create(savedInstance: SavedInstance) {
        rvVideo.layoutManager = LinearLayoutManager(this)


//        rvVideo.adapter = rvadapter
//        val randomVideoCollectionGenerator = RandomVideoCollectionGenerator()
//        rvadapter.swap(randomVideoCollectionGenerator.generate(100).videos)
        rvVideo.adapter = rvadapterModel
        val randomVideoCollectionGenerator = RandomVideoCollectionGenerator()
        rvadapterModel.swap(randomVideoCollectionGenerator.generate(100).videos.toAdapter())
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