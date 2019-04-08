package com.acv.manfred.curriculum.ui.form

import android.view.View
import com.acv.manfred.curriculum.R
import com.acv.uikit.adapterModel.ViewHolderWithParent
import kotlinx.android.synthetic.main.video_renderer.*

class AudioHolder(
    view: View
) : ViewHolderWithParent<Media, Audio>(view) {
    override fun Audio.view() {
        tv_label.text = thumbnail
        tv_title.text = title
    }
}

class AudioAdapterHolder(
    view: View
) : ViewHolderWithParent<MediaAdapter, AudioAdapter>(view) {
    override fun AudioAdapter.view() {
        tv_label.text = thumbnail
        tv_title.text = title
    }
}