package com.acv.manfred.curriculum.ui.form

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
