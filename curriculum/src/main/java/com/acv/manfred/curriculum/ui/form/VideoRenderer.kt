package com.acv.manfred.curriculum.ui.form

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.acv.manfred.curriculum.R
import com.acv.uikit.renderer.Renderer
import kotlinx.android.synthetic.main.video_renderer.*

abstract class VideoRenderer : Renderer<Video>(){
    override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View =
        inflater.inflate(R.layout.video_renderer, parent, false)

    override fun render() {
        val video = content!!
        renderThumbnail(video)
        renderTitle(video)
        renderMarker(video)
        renderLabel()
    }

    private fun renderThumbnail(video: Video) {
//        Picasso.with(getContext()).cancelRequest(iv_thumbnail);
//        Picasso.with(getContext())
//            .load(video.getThumbnail())
//            .placeholder(R.drawable.ic_airplay)
//            .into(iv_thumbnail);
    }

    private fun renderTitle(video: Video) =
        tv_title.setText(video.title)

    protected fun getLabel(): TextView =
        tv_label

    protected fun getMarker(): ImageView =
        iv_marker

    protected abstract fun renderLabel()

    protected abstract fun renderMarker(video: Video)

    override fun hookListeners(rootView: View) {
       iv_thumbnail.setOnClickListener {
           val video = content
           Toast.makeText(getContext(), "Video clicked. Title = " + video!!.title, Toast.LENGTH_LONG).show()
       }
    }
}
