package com.acv.uikit.chip

import android.content.Context
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import arrow.core.None
import arrow.core.some
import com.acv.uikit.R
import com.acv.uikit.common.Errorable
import com.acv.uikit.common.click
import com.acv.uikit.popup.Popup
import com.acv.uikit.popup.PopupAdapter
import com.acv.uikit.popup.PopupModel
import kotlinx.android.synthetic.main.chips_view.view.*

class ChipsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Errorable {

    private lateinit var popups: List<PopupAdapter>
    private var chips: List<ChipModel> = listOf()
//
//    private val rvadapterModel by lazy {
//        RVAdapter<PopupAdapter> { it, v ->
//            PopupAdapterHolder(v) {
//                //                rvOptions.visibility = View.GONE
//                chips = chips.plus( ChipModel(it.title, it.title, Closelable {
//                    chips= chips.minus(it)
//                    swap(chips) { a, b -> a.id == b.id }
//                }.some()))
//
//            }
//        }
//    }

    var state: Boolean = true

    override fun errorK(value: String) {
//        error = value
    }

//    var text
//        get() = headerAction.text
//        set(value) {
//            headerAction.text = value
//        }

//    var error
//        get() = tvError.text
//        set(value) {
//            tvError.text = value
//        }

//    var textAction
//        get() = headerAction.textAction
//        set(value) {
//            headerAction.textAction = value
//        }

//    var icon: Option<Drawable> = None
//        set(value) {
//            headerAction.icon = value
//            field = value
//        }

    fun swap(newItems: List<ChipModel>, compare: (ChipModel, ChipModel) -> Boolean) {
        chipView.swap(newItems, compare)
    }

    init {
        inflate(context, R.layout.chips_view, this)
        orientation = VERTICAL

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ChipsView, 0, 0)

        try {
//            text = a.getString(R.styleable.ChipHeaderAction_text) ?: ""
//            error = a.getString(R.styleable.ChipHeaderAction_error)
//            textAction = a.getString(R.styleable.ChipHeaderAction_textAction) ?: ""
//            icon = a.getDrawable(R.styleable.ChipHeaderAction_icon).some()
        } finally {
            a.recycle()
        }

//        rvOptions.run {
//            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(DividerItemDecoration(context, VERTICAL))
//            isNestedScrollingEnabled = true
//            adapter = rvadapterModel
//            itemAnimator = DefaultItemAnimator()
//        }
    }

    fun action(list: List<PopupAdapter>): Unit =
        btnAction click {
            popups = list
            //            if (state) showComponents() else hideComponents()
//            f(it)
//            rvadapterModel.swap(list)
//            rvOptions.visibility = View.VISIBLE
            Popup(context).render(PopupModel(box, list) { x, p ->
                chips = chips.plus(ChipModel(x.title, x.title, None, Closelable {
                    chips = chips.minus(it)
                    swap(chips) { a, b -> a.id == b.id }
                }.some()))
                swap(chips) { a, b -> a.id == b.id }
            })
        }

    private fun showComponents() {
        state = false

//        val a = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15f, resources.getDisplayMetrics())
//        val animator = ObjectAnimator.ofFloat(label, "textSize", a)
//        animator.setDuration(200L)
//        animator.start()


//        val constraintSet = ConstraintSet()
//        constraintSet.clone(context, R.layout.chip_header_costraint_hint)
//
//        val transition = ChangeBounds()
//        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
//        transition.duration = 1200
//
//        TransitionManager.beginDelayedTransition(constraint, transition)
//        constraintSet.applyTo(constraint)
        val set = ConstraintSet()
        set.clone(constraint)
//        set.connect(label.id, ConstraintSet.VERTICAL, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
//
//        set.connect(label.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
        set.setVerticalBias(label.id, -1f)
//        set.constrainWidth(R.id.myButton, ConstraintSet.WRAP_CONTENT)
        val transition = AutoTransition()
        transition.interpolator = LinearInterpolator()
        transition.duration = 200
        TransitionManager.beginDelayedTransition(constraint, transition)
        set.applyTo(constraint)
//        ObjectAnimator.ofFloat(label, "translationY", 50f).apply {
//            duration = 500
//            start()
//        }

        label.isEnabled = false
//        box.isEnabled = false
    }

    private fun hideComponents() {
        state = true
//        val constraintSet = ConstraintSet()
//        constraintSet.clone(context, R.layout.chips_view)
//
//        val transition = ChangeBounds()
//        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
//        transition.duration = 1200
//
//        TransitionManager.beginDelayedTransition(constraint, transition)
//        constraintSet.applyTo(constraint)

//        val a = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15f, resources.getDisplayMetrics())
//        val animator = ObjectAnimator.ofFloat(label, "textSize", a)
//        animator.setDuration(200L)
//        animator.start()


        val set = ConstraintSet()
        set.clone(constraint)
//        set.connect(label.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
//
//        set.connect(label.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)

//        set.constrainWidth(R.id.myButton, ConstraintSet.WRAP_CONTENT)
        set.setVerticalBias(label.id, 0f)

        val transition = AutoTransition()
        transition.interpolator = LinearInterpolator()
        transition.duration = 200
        TransitionManager.beginDelayedTransition(constraint, transition)
        set.applyTo(constraint)
//        ObjectAnimator.ofFloat(label, "translationY", 0f).apply {
//            duration = 500
//            start()
//        }


        label.isEnabled = true
//        box.isEnabled = true
    }
}