package com.acv.manfredcv.presentation.common.activity

import android.view.KeyEvent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.acv.manfredcv.presentation.common.fragment.BaseFragment


abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    onBackPressed()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.fragments.map { (it as BaseFragment).onBackPressed() }
//            supportFragmentManager.popBackStack()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed()
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
}
