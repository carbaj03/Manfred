package com.acv.manfred.curriculum.ui.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.arch.Provider
import com.acv.manfred.curriculum.ui.common.navigator.ExtraExtensionsFr

abstract class BaseFragment : Fragment(), ExtraExtensionsFr, Provider {
    override val baseActivity: BaseActivity get() = activity as BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(getLayout(), container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreate()
    }

    protected abstract fun getLayout(): Int
    protected abstract fun onCreate()
}