package com.acv.manfred.curriculum.ui.form

import android.view.View
import com.acv.manfred.curriculum.R
import com.acv.uikit.adapterRender.ViewHolderWithParent
import kotlinx.android.synthetic.main.video_renderer.*

class VideoHolder(
    view: View
) : ViewHolderWithParent<Media, Video>(R.layout.video_renderer, view) {
    override fun Video.view() {
        tv_label.text = thumbnail
        tv_title.text = title
    }
}