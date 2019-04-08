package com.acv.manfred.curriculum.ui.form

import android.view.View
import com.acv.manfred.curriculum.R
import com.acv.uikit.adapterModel.ViewHolderWithParent
import kotlinx.android.synthetic.main.video_renderer.*

class VideoHolder(
    view: View
) : ViewHolderWithParent<Media, Video>(view) {
    override fun Video.view() {
        tv_label.text = thumbnail
        tv_title.text = title
    }
}

class VideoAdapterHolder(
    view: View
) : ViewHolderWithParent<MediaAdapter, VideoAdapter>(view) {
    override fun VideoAdapter.view() {
        tv_label.text = thumbnail
        tv_title.text = title
    }
}