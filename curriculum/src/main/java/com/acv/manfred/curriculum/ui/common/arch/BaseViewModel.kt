package com.acv.manfred.curriculum.ui.common.arch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acv.manfred.curriculum.presentation.BaseView
import com.acv.manfred.curriculum.presentation.Failure
import com.acv.manfred.curriculum.presentation.Transaction
import com.acv.manfred.curriculum.domain.model.BaseError


open class BaseViewModel : ViewModel(), BaseView {
    val state: MutableLiveData<Transaction>
            by lazy { MutableLiveData<Transaction>() }

    override fun catchError(baseError: BaseError) {
        state.value = Failure(baseError)
    }
}