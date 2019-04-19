package com.acv.manfred.curriculum.ui.common.fragment

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager
import com.acv.manfred.curriculum.R


class ViewPagerContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    init {
        id = R.id.fragment_container
    }
}