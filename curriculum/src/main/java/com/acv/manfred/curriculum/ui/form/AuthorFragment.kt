package com.acv.manfred.curriculum.ui.form

import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import kotlinx.android.synthetic.main.view_author.*

class AuthorFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.view_author

    override fun onCreate() {
        val adapter = ViewPagerAdapter(baseActivity.supportFragmentManager)
        adapter.addFragment(ProfileFragment(), "Profile")
        adapter.addFragment(IntroFragment(), "Intro")
        adapter.addFragment(ProfesionalGoalsFragment(), "Professional Goals")
        fragment_container2.adapter = adapter
        tabs.setupWithViewPager(fragment_container2)
    }


}


