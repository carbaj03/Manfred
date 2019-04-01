package com.fintonic.uikit.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


infix fun ViewGroup.inflate(res: Int) =
        LayoutInflater.from(context).inflate(res, this, false)


infix fun View.click(f: () -> Unit) =
        setOnClickListener { f() }