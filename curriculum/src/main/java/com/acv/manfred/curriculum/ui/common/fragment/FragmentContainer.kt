package com.acv.manfred.curriculum.ui.common.fragment

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.acv.manfred.curriculum.R


class FragmentContainer @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        id = R.id.fragment_container
    }
}