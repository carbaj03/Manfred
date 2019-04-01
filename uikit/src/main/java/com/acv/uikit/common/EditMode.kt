package com.acv.uikit.common

interface EditMode {

    fun init(isInEditMode : Boolean){
        if(isInEditMode) editMode() else normalMode()
    }

    fun editMode()
    fun normalMode()
}