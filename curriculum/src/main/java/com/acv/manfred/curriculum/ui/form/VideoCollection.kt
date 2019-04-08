package com.acv.manfred.curriculum.ui.form


import com.acv.uikit.renderer.ListAdapteeCollection

/**
 * Class created to represent a list of videos. This class has been created to store videos,
 * encapsulate the collection usage and implements the AdapteeCollection interface needed by
 * RendererAdapter. You don't have to create your own AdapteeCollection if you don't need it, you
 * can use ListAdapteeColleciton.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
class VideoCollection(val videos: MutableList<Media>) : ListAdapteeCollection<Media>(videos)
