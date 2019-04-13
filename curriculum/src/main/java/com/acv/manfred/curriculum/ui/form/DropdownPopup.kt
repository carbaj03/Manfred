//package com.acv.manfred.curriculum.ui.form
//
//import android.content.Context
//import android.graphics.Rect
//import android.util.AttributeSet
//import android.view.View
//import android.view.ViewTreeObserver
//import android.widget.*
//import androidx.appcompat.widget.ListPopupWindow
//import androidx.appcompat.widget.ViewUtils
//import androidx.core.view.ViewCompat
//
//class DropdownPopup(val context: Context, attrs: AttributeSet, defStyleAttr: Int) : ListPopupWindow(context, attrs, defStyleAttr) {
//    private var mAdapter: ListAdapter? = null
//    private val mVisibleRect = Rect()
//
//    private lateinit var targetView : View
//
//    init {
//        anchorView = targetView
//        isModal = true
//        promptPosition = ListPopupWindow.POSITION_PROMPT_ABOVE
//
//        setOnItemClickListener { parent, v, position, id ->
//            targetView.setSelection(position)
//            if (getOnItemClickListener() != null) {
//                targetView.performItemClick(v, position, mAdapter!!.getItemId(position))
//            }
//            dismiss()
//        }
//    }
//
//    override fun setAdapter(adapter: ListAdapter?) {
//        super.setAdapter(adapter)
//        mAdapter = adapter
//    }
//
//
//    internal fun computeContentWidth() {
//        val background = background
//        var hOffset = 0
//        if (background != null) {
//            background.getPadding(mTempRect)
//            hOffset = if (ViewUtils.isLayoutRtl(targetView))
//                mTempRect.right
//            else
//                -mTempRect.left
//        } else {
//            mTempRect.right = 0
//            mTempRect.left = mTempRect.right
//        }
//
//        val spinnerPaddingLeft = targetView.getPaddingLeft()
//        val spinnerPaddingRight = targetView.getPaddingRight()
//        val spinnerWidth = targetView.getWidth()
//        if (mDropDownWidth == ListPopupWindow.WRAP_CONTENT) {
//            var contentWidth = compatMeasureContentWidth(
//                mAdapter as SpinnerAdapter?, getBackground()
//            )
//            val contentWidthLimit = context.getResources().getDisplayMetrics().widthPixels - mTempRect.left - mTempRect.right
//            if (contentWidth > contentWidthLimit) {
//                contentWidth = contentWidthLimit
//            }
//            setContentWidth(
//                Math.max(
//                    contentWidth, spinnerWidth - spinnerPaddingLeft - spinnerPaddingRight
//                )
//            )
//        } else if (mDropDownWidth == ListPopupWindow.MATCH_PARENT) {
//            setContentWidth(spinnerWidth - spinnerPaddingLeft - spinnerPaddingRight)
//        } else {
//            setContentWidth(mDropDownWidth)
//        }
//        if (ViewUtils.isLayoutRtl(targetView)) {
//            hOffset += spinnerWidth - spinnerPaddingRight - width
//        } else {
//            hOffset += spinnerPaddingLeft
//        }
//        horizontalOffset = hOffset
//    }
//
//    override fun show() {
//        val wasShowing = isShowing
//
//        computeContentWidth()
//
//        inputMethodMode = ListPopupWindow.INPUT_METHOD_NOT_NEEDED
//        super.show()
//        val listView = listView
//        listView!!.choiceMode = ListView.CHOICE_MODE_SINGLE
//        setSelection(targetView.getSelectedItemPosition())
//
//        if (wasShowing) {
//            // Skip setting up the layout/dismiss listener below. If we were previously
//            // showing it will still stick around.
//            return
//        }
//
//        // Make sure we hide if our anchor goes away.
//        // TODO: This might be appropriate to push all the way down to PopupWindow,
//        // but it may have other side effects to investigate first. (Text editing handles, etc.)
//        val vto = getViewTreeObserver()
//        if (vto != null) {
//            val layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
//                if (!isVisibleToUser(targetView)) {
//                    dismiss()
//                } else {
//                    computeContentWidth()
//
//                    // Use super.show here to update; we don't want to move the selected
//                    // position or adjust other things that would be reset otherwise.
//                    super@DropdownPopup.show()
//                }
//            }
//            vto!!.addOnGlobalLayoutListener(layoutListener)
//            setOnDismissListener {
//                val vto = getViewTreeObserver()
//                if (vto != null) {
//                    vto!!.removeGlobalOnLayoutListener(layoutListener)
//                }
//            }
//        }
//    }
//
//    /**
//     * Simplified version of the the hidden View.isVisibleToUser()
//     */
//    internal fun isVisibleToUser(view: View): Boolean {
//        return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(mVisibleRect)
//    }
//}