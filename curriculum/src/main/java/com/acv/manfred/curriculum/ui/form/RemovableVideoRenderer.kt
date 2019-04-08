package com.acv.manfred.curriculum.ui.form;

import kotlinx.android.synthetic.main.video_renderer.*


class RemovableVideoRenderer(
    var removeItemListener: Listener
) : VideoRenderer() {
    interface Listener {
        fun onRemoveButtonTapped(video: Video)
    }

    fun RemovableVideoRenderer(removeItemListener: Listener) {
        this.removeItemListener = removeItemListener;
    }

    override fun renderLabel() {
        val deleteLabel = "delete Labl"
        getLabel().setText(deleteLabel);
    }

    override fun renderMarker(video: Video) {
        tv_label.setOnClickListener { removeItemListener.onRemoveButtonTapped(content!!) }
        iv_marker.setOnClickListener {
            content!!.liked = !content!!.liked
            renderMarker(content!!)
        }
    }
}
