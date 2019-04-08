package com.acv.manfred.curriculum.ui.form

import com.acv.manfred.curriculum.R
import com.acv.uikit.adapterModel.AdapterModel
import com.acv.uikit.adapterModel.Layout

sealed class Media

data class Video(
    var favorite: Boolean = false,
    var liked: Boolean = false,
    var live: Boolean = false,
    var thumbnail: String = "",
    var title: String = ""
) : Media()

data class Audio(
    var favorite: Boolean = false,
    var liked: Boolean = false,
    var live: Boolean = false,
    var thumbnail: String = "",
    var title: String = ""
) : Media()

fun List<Media>.toAdapter(): List<MediaAdapter> =
    map {
        when (it) {
            is Video -> it.toAdapter()
            is Audio -> it.toAdapter()
        }
    }

fun Video.toAdapter(): VideoAdapter =
    VideoAdapter(favorite, liked, live, thumbnail, title)

fun Audio.toAdapter(): AudioAdapter =
    AudioAdapter(favorite, liked, live, thumbnail, title)

sealed class MediaAdapter(layout: Layout) : AdapterModel(layout)

data class VideoAdapter(
    var favorite: Boolean = false,
    var liked: Boolean = false,
    var live: Boolean = false,
    var thumbnail: String = "",
    var title: String = ""
) : MediaAdapter(R.layout.video_renderer)

data class AudioAdapter(
    var favorite: Boolean = false,
    var liked: Boolean = false,
    var live: Boolean = false,
    var thumbnail: String = "",
    var title: String = ""
) : MediaAdapter(R.layout.audio_renderer)
