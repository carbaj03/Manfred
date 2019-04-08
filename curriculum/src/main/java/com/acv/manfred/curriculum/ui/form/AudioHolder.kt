package com.acv.manfred.curriculum.ui.form

import android.view.View
import com.acv.manfred.curriculum.R
import com.acv.uikit.adapterRender.ViewHolderWithParent
import kotlinx.android.synthetic.main.video_renderer.*

class AudioHolder(
    view: View
) : ViewHolderWithParent<Media, Audio>(R.layout.audio_renderer, view) {
    override fun Audio.view() {
        tv_label.text = thumbnail
        tv_title.text = title
    }
}