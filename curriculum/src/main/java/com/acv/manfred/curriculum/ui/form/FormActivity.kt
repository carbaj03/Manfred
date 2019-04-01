package com.acv.manfred.curriculum.ui.form

import android.os.Bundle
import com.acv.manfred.curriculum.R
import com.acv.manfredcv.presentation.common.activity.BaseActivity
import com.acv.uikit.input.InputModel
import com.acv.uikit.input.InputText
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        tilDescription { copy(style = InputText) }

    }


}