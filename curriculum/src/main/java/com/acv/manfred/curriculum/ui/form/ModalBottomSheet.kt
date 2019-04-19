package com.acv.manfred.curriculum.ui.form

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.acv.manfred.curriculum.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(context!!, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.modal_bottom_sheet, container, false)
    }
}


//class BottomSheetDialog : AppCompatDialog {
//
//    var behavior: BottomSheetBehavior<FrameLayout>? = null
//        private set
//
//    internal var cancelable = true
//    private var canceledOnTouchOutside = true
//    private var canceledOnTouchOutsideSet: Boolean = false
//
//    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
//        override fun onStateChanged(
//            bottomSheet: View, @BottomSheetBehavior.State newState: Int
//        ) {
//            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                cancel()
//            }
//        }
//
//        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//    }
//
//    companion object{
//        private fun getThemeResId(context: Context, themeId: Int): Int {
//            var themeId = themeId
//            if (themeId == 0) {
//                // If the provided theme is 0, then retrieve the dialogTheme from our theme
//                val outValue = TypedValue()
//                if (context.theme.resolveAttribute(com.google.android.material.R.attr.bottomSheetDialogTheme, outValue, true)) {
//                    themeId = outValue.resourceId
//                } else {
//                    // bottomSheetDialogTheme is not provided; we default to our light theme
//                    themeId = com.google.android.material.R.style.Theme_Design_Light_BottomSheetDialog
//                }
//            }
//            return themeId
//        }
//    }
//
//    @JvmOverloads constructor(context: Context, @StyleRes theme: Int = 0) : super(context, getThemeResId(context, theme)) {
//        // We hide the title bar for any style configuration. Otherwise, there will be a gap
//        // above the bottom sheet when it is expanded.
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
//    }
//
//    protected constructor(
//        context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener
//    ) : super(context, cancelable, cancelListener) {
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.cancelable = cancelable
//    }
//
//    override fun setContentView(@LayoutRes layoutResId: Int) {
//        super.setContentView(wrapInBottomSheet(layoutResId, null, null))
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val window = window
//        if (window != null) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                window!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            }
//            window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        }
//    }
//
//    override fun setContentView(view: View) {
//        super.setContentView(wrapInBottomSheet(0, view, null))
//    }
//
//    override fun setContentView(view: View, params: ViewGroup.LayoutParams?) {
//        super.setContentView(wrapInBottomSheet(0, view, params))
//    }
//
//    override fun setCancelable(cancelable: Boolean) {
//        super.setCancelable(cancelable)
//        if (this.cancelable != cancelable) {
//            this.cancelable = cancelable
//            if (behavior != null) {
//                behavior!!.isHideable = cancelable
//            }
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        if (behavior != null && behavior!!.state == BottomSheetBehavior.STATE_HIDDEN) {
//            behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//        }
//    }
//
//    override fun setCanceledOnTouchOutside(cancel: Boolean) {
//        super.setCanceledOnTouchOutside(cancel)
//        if (cancel && !cancelable) {
//            cancelable = true
//        }
//        canceledOnTouchOutside = cancel
//        canceledOnTouchOutsideSet = true
//    }
//
//    private fun wrapInBottomSheet(layoutResId: Int, view: View?, params: ViewGroup.LayoutParams?): View {
//        var view = view
//        val container =
//            View.inflate(context, com.google.android.material.R.layout.design_bottom_sheet_dialog, null) as FrameLayout
//        val coordinator =
//            container.findViewById<View>(com.google.android.material.R.id.coordinator) as CoordinatorLayout
//        if (layoutResId != 0 && view == null) {
//            view = layoutInflater.inflate(layoutResId, coordinator, false)
//        }
//        val bottomSheet =
//            coordinator.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
//        behavior = BottomSheetBehavior.from(bottomSheet)
//        behavior!!.setBottomSheetCallback(bottomSheetCallback)
//        behavior!!.isHideable = cancelable
//        if (params == null) {
//            bottomSheet.addView(view)
//        } else {
//            bottomSheet.addView(view, params)
//        }
//        // We treat the CoordinatorLayout as outside the dialog though it is technically inside
//        coordinator
//            .findViewById<View>(com.google.android.material.R.id.touch_outside)
//            .setOnClickListener {
//                if (cancelable && isShowing && shouldWindowCloseOnTouchOutside()) {
//                    cancel()
//                }
//            }
//        // Handle accessibility events
//        ViewCompat.setAccessibilityDelegate(
//            bottomSheet,
//            object : AccessibilityDelegateCompat() {
//                override fun onInitializeAccessibilityNodeInfo(
//                    host: View, info: AccessibilityNodeInfoCompat
//                ) {
//                    super.onInitializeAccessibilityNodeInfo(host, info)
//                    if (cancelable) {
//                        info.addAction(AccessibilityNodeInfoCompat.ACTION_DISMISS)
//                        info.isDismissable = true
//                    } else {
//                        info.isDismissable = false
//                    }
//                }
//
//                override fun performAccessibilityAction(host: View, action: Int, args: Bundle): Boolean {
//                    if (action == AccessibilityNodeInfoCompat.ACTION_DISMISS && cancelable) {
//                        cancel()
//                        return true
//                    }
//                    return super.performAccessibilityAction(host, action, args)
//                }
//            })
//        bottomSheet.setOnTouchListener(
//            object : View.OnTouchListener {
//                override fun onTouch(view: View, event: MotionEvent): Boolean {
//                    // Consume the event and prevent it from falling through
//                    return true
//                }
//            })
//        return container
//    }
//
//    internal fun shouldWindowCloseOnTouchOutside(): Boolean {
//        if (!canceledOnTouchOutsideSet) {
//            val a = context
//                .obtainStyledAttributes(intArrayOf(android.R.attr.windowCloseOnTouchOutside))
//            canceledOnTouchOutside = a.getBoolean(0, true)
//            a.recycle()
//            canceledOnTouchOutsideSet = true
//        }
//        return canceledOnTouchOutside
//    }
//
//
//}
