package com.acv.uikit.material;

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.TintTypedArray
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import com.google.android.material.R
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.internal.DescendantOffsetUtils
import com.google.android.material.internal.ThemeEnforcement
import com.google.android.material.resources.MaterialResources
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.textfield.TextInputLayout.*

class Box @JvmOverloads constructor(
  context: Context,
  val attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

  private val LABEL_SCALE_ANIMATION_DURATION = 167L
  private val DEF_STYLE_RES = R.style.Widget_Design_TextInputLayout

  private lateinit var editText: View
  private val inputFrame: FrameLayout
  val collapsingTextHelper = CollapsingTextHelper(this)

  @BoxBackgroundMode private var boxBackgroundMode: Int = 0
  private var boxBackground: MaterialShapeDrawable? = null
  private var boxUnderline: MaterialShapeDrawable? = null
  private var boxCollapsedPaddingTopPx: Int = 0
  private var boxStrokeWidthPx: Int = 0
  private var boxStrokeWidthDefaultPx: Int = 0
  private var boxStrokeWidthFocusedPx: Int = 0
  private var boxLabelCutoutPaddingPx: Int = 0
  @ColorInt private var boxStrokeColor: Int = 0
  @ColorInt private var boxBackgroundColor: Int = 0

  private var hintEnabled: Boolean = false
  private var hint: CharSequence? = null
  private var hintAnimationEnabled: Boolean = false
  private lateinit var animator: ValueAnimator


//  private val indicatorViewController = IndicatorViewController(this)

  private var defaultHintTextColor: ColorStateList? = null
  private var focusedTextColor: ColorStateList? = null


  private val tmpRectF = RectF()
  private val tmpBoundsRect = Rect()
  private val tmpRect = Rect()

  @ColorInt private var disabledColor: Int = 0
  @ColorInt private var defaultStrokeColor: Int = 0
  @ColorInt private var hoveredStrokeColor: Int = 0
  @ColorInt private var focusedStrokeColor: Int = 0


  @ColorInt private var defaultFilledBackgroundColor: Int = 0
  @ColorInt private var disabledFilledBackgroundColor: Int = 0
  @ColorInt private var hoveredFilledBackgroundColor: Int = 0

  override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
    if (child is EditText) {
      // Make sure that the EditText is vertically at the bottom, so that it sits on the
      // EditText's underline
      val flp = FrameLayout.LayoutParams(params)
      flp.gravity = Gravity.CENTER_VERTICAL or (flp.gravity and Gravity.VERTICAL_GRAVITY_MASK.inv())
      inputFrame.addView(child, flp)

      // Now use the EditText's LayoutParams as our own and update them to make enough space
      // for the label
      inputFrame.layoutParams = params
      updateInputLayoutMargins()

      setEditText(child)
    } else {
      // Carry on adding the View...
      super.addView(child, index, params)
    }
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)

    if (editText != null) {
      val rect = tmpRect
      DescendantOffsetUtils.getDescendantRect(this, editText, rect)
      updateBoxUnderlineBounds(rect)

      if (hintEnabled) {
        collapsingTextHelper.setCollapsedBounds(calculateCollapsedTextBounds(rect))
        collapsingTextHelper.setExpandedBounds(calculateExpandedTextBounds(rect))
        collapsingTextHelper.recalculate()

        // If the label should be collapsed, set the cutout bounds on the CutoutDrawable to make
        // sure it draws with a cutout in draw().
        if (cutoutEnabled()) {
          openCutout()
        }
      }
    }
  }

  private fun calculateCollapsedTextBounds(rect: Rect): Rect {
    if (editText == null) {
      throw IllegalStateException()
    }
    val bounds = tmpBoundsRect

    bounds.bottom = rect.bottom
    when (boxBackgroundMode) {
      BOX_BACKGROUND_OUTLINE -> {
        bounds.left = rect.left + editText.paddingLeft
        bounds.top = rect.top - calculateLabelMarginTop()
        bounds.right = rect.right - editText.paddingRight
        return bounds
      }
      BOX_BACKGROUND_FILLED -> {
        bounds.left = rect.left + editText.paddingLeft
        bounds.top = rect.top + boxCollapsedPaddingTopPx
        bounds.right = rect.right - editText.paddingRight
        return bounds
      }
      else -> {
        bounds.left = rect.left + editText.paddingLeft
        bounds.top = paddingTop
        bounds.right = rect.right - editText.paddingRight
        return bounds
      }
    }
  }

  private fun calculateExpandedTextBounds(rect: Rect): Rect {
    if (editText == null) {
      throw IllegalStateException()
    }

    val bounds = tmpBoundsRect

    val labelHeight = collapsingTextHelper.expandedTextHeight

    bounds.left = rect.left + editText.paddingLeft
    bounds.top = calculateExpandedLabelTop(rect, labelHeight)
    bounds.right = rect.right - editText.paddingRight
    bounds.bottom = calculateExpandedLabelBottom(rect, bounds, labelHeight)

    return bounds
  }

  private fun calculateExpandedLabelTop(rect: Rect, labelHeight: Float): Int {
    return if (boxBackgroundMode == BOX_BACKGROUND_FILLED) {
      (rect.centerY() - labelHeight / 2).toInt()
    } else rect.top + editText.paddingTop
  }

  private fun calculateExpandedLabelBottom(rect: Rect, bounds: Rect, labelHeight: Float): Int {
    return if (boxBackgroundMode == BOX_BACKGROUND_FILLED) {
      // Add the label's height to the top of the bounds rather than calculating from the vertical
      // center for both the top and bottom of the label. This prevents a potential fractional loss
      // of label height caused by the float to int conversion.
      (bounds.top + labelHeight).toInt()
    } else rect.bottom - editText.paddingBottom
  }

  private fun updateBoxUnderlineBounds(bounds: Rect) {
    if (boxUnderline != null) {
      val top = bounds.bottom - boxStrokeWidthFocusedPx
      boxUnderline?.setBounds(bounds.left, top, bounds.right, bounds.bottom)
    }
  }

  private fun setEditText(editText: EditText) {
    // If we already have an EditText, throw an exception
//    if (this.editText != null) {
//      throw IllegalArgumentException("We already have an EditText, can only have one")
//    }

//    if (editText !is TextInputEditText) {
//      Log.i(
//        LOG_TAG,
//        "EditText added is not a TextInputEditText. Please switch to using that" + " class instead."
//      )
//    }

    this.editText = editText
    onApplyBoxBackgroundMode()
//    setTextInputAccessibilityDelegate(AccessibilityDelegate(this))

    // Use the EditText's typeface, and its text size for our expanded text.
//    collapsingTextHelper.setTypefaces(this.editText.typeface)
//    collapsingTextHelper.expandedTextSize = this.editText.textSize
//
//    val editTextGravity = this.editText.gravity
//    collapsingTextHelper.collapsedTextGravity = Gravity.TOP or (editTextGravity and Gravity.VERTICAL_GRAVITY_MASK.inv())
//    collapsingTextHelper.expandedTextGravity = editTextGravity

    // Add a TextWatcher so that we know when the text input has changed.
//    this.editText.addTextChangedListener(
//      object : TextWatcher {
//        override fun afterTextChanged(s: Editable) {
//          updateLabelState(!restoringSavedState)
//          if (counterEnabled) {
//            updateCounter(s.length)
//          }
//        }
//
//        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//      })

    // Use the EditText's hint colors if we don't have one set
//    if (defaultHintTextColor == null) {
//      defaultHintTextColor = this.editText.hintTextColors
//    }

    // If we do not have a valid hint, try and retrieve it from the EditText, if enabled
    if (hintEnabled) {
      if (TextUtils.isEmpty(hint)) {
        // Save the hint so it can be restored on dispatchProvideAutofillStructure();
//        originalHint = this.editText.hint
//        setHint(originalHint)
        // Clear the EditText's hint as we will display it ourselves
//        this.editText.setHint(null)
      }
//      this.isProvidingHint = true
    }

//    if (counterView != null) {
//      updateCounter(this.editText.text.length)
//    }
    updateEditTextBackground()

//    indicatorViewController.adjustIndicatorPadding()

//    updateIconViewOnEditTextAttached(
//      startIconView,
//      R.dimen.mtrl_textinput_start_icon_padding_start,
//      R.dimen.mtrl_textinput_start_icon_padding_end
//    )
//    updateIconViewOnEditTextAttached(
//      endIconView,
//      R.dimen.mtrl_textinput_end_icon_padding_start,
//      R.dimen.mtrl_textinput_end_icon_padding_end
//    )
//    dispatchOnEditTextAttached()

    // Update the label visibility with no animation, but force a state change
    updateLabelState(false, true)
  }

  private fun updateLabelState(animate: Boolean, force: Boolean) {
    val isEnabled = isEnabled
    //Todo text
    val hasText = editText != null && !TextUtils.isEmpty("")
    val hasFocus = editText != null && editText.hasFocus()
//    val errorShouldBeShown = indicatorViewController.errorShouldBeShown()

    // Set the expanded and collapsed labels to the default text color.
    if (defaultHintTextColor != null) {
      collapsingTextHelper.collapsedTextColor = defaultHintTextColor
      collapsingTextHelper.expandedTextColor = defaultHintTextColor
    }

    // Set the collapsed and expanded label text colors based on the current state.
    if (!isEnabled) {
      collapsingTextHelper.collapsedTextColor = ColorStateList.valueOf(disabledColor)
      collapsingTextHelper.expandedTextColor = ColorStateList.valueOf(disabledColor)
//    } else if (errorShouldBeShown) {
//      collapsingTextHelper.collapsedTextColor = indicatorViewController.errorViewTextColors
//    } else if (counterOverflowed && counterView != null) {
//      collapsingTextHelper.collapsedTextColor = counterView.getTextColors()
    } else if (hasFocus && focusedTextColor != null) {
      collapsingTextHelper.collapsedTextColor = focusedTextColor
    } // If none of these states apply, leave the expanded and collapsed colors as they are.

    if (hasText || isEnabled() && (hasFocus )) {
      // We should be showing the label so do so if it isn't already
      if (force) {
        collapseHint(animate)
      }
    } else {
      // We should not be showing the label so hide it
      if (force) {
        expandHint(animate)
      }
    }
  }

  private fun collapseHint(animate: Boolean) {
    if (animator != null && animator.isRunning) {
      animator.cancel()
    }
    if (animate && hintAnimationEnabled) {
      animateToExpansionFraction(1f)
    } else {
      collapsingTextHelper.expansionFraction = 1f
    }
    if (cutoutEnabled()) {
      openCutout()
    }
  }

  private fun expandHint(animate: Boolean) {
    if (animator != null && animator.isRunning()) {
      animator.cancel()
    }
    if (animate && hintAnimationEnabled) {
      animateToExpansionFraction(0f)
    } else {
      collapsingTextHelper.expansionFraction = 0f
    }
    if (cutoutEnabled() && (boxBackground as CutoutDrawable).hasCutout()) {
      closeCutout()
    }
  }

  private fun cutoutEnabled(): Boolean {
    return hintEnabled && !TextUtils.isEmpty(hint) && boxBackground is CutoutDrawable
  }

  private fun closeCutout() {
    if (cutoutEnabled()) {
      (boxBackground as CutoutDrawable).removeCutout()
    }
  }

  private fun applyCutoutPadding(cutoutBounds: RectF) {
    cutoutBounds.left -= boxLabelCutoutPaddingPx
    cutoutBounds.top -= boxLabelCutoutPaddingPx
    cutoutBounds.right += boxLabelCutoutPaddingPx
    cutoutBounds.bottom += boxLabelCutoutPaddingPx
  }

  private fun openCutout() {
    if (!cutoutEnabled()) {
      return
    }
    val cutoutBounds = tmpRectF
    collapsingTextHelper.getCollapsedTextActualBounds(cutoutBounds)
    applyCutoutPadding(cutoutBounds)
    // Offset the cutout bounds by the TextInputLayout's left padding to ensure that the cutout is
    // inset relative to the TextInputLayout's bounds.
    cutoutBounds.offset((-paddingLeft).toFloat(), 0f)
    (boxBackground as CutoutDrawable).setCutout(cutoutBounds)
  }

  private fun animateToExpansionFraction(target: Float) {
    if (collapsingTextHelper.expansionFraction == target) {
      return
    }
    if (this.animator == null) {
      this.animator = ValueAnimator()
      this.animator.interpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR
      this.animator.duration = LABEL_SCALE_ANIMATION_DURATION
      this.animator.addUpdateListener { animator ->
        collapsingTextHelper.expansionFraction = animator.animatedValue as Float
      }
    }
    this.animator.setFloatValues(collapsingTextHelper.expansionFraction, target)
    this.animator.start()
  }

  @SuppressLint("RestrictedApi")
  internal fun updateEditTextBackground() {
    // Only update the color filter for the legacy text field, since we can directly change the
    // Paint colors of the MaterialShapeDrawable box background without having to use color filters.
    if (editText == null || boxBackgroundMode != BOX_BACKGROUND_NONE) {
      return
    }

    var editTextBackground: Drawable? = editText.background ?: return

    if (androidx.appcompat.widget.DrawableUtils.canSafelyMutateDrawable(editTextBackground!!)) {
      editTextBackground = editTextBackground.mutate()
    }

//    if (indicatorViewController.errorShouldBeShown()) {
//      // Set a color filter for the error color
//      editTextBackground!!.colorFilter = AppCompatDrawableManager.getPorterDuffColorFilter(
//        indicatorViewController.errorViewCurrentTextColor, PorterDuff.Mode.SRC_IN
//      )
//    } else if (counterOverflowed && counterView != null) {
//       Set a color filter of the counter color
//      editTextBackground!!.colorFilter = AppCompatDrawableManager.getPorterDuffColorFilter(
//        counterView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN
//      )
//    } else {
      // Else reset the color filter and refresh the drawable state so that the
      // normal tint is used
      DrawableCompat.clearColorFilter(editTextBackground!!)
      editText.refreshDrawableState()
//    }
  }

  fun setTextInputAccessibilityDelegate(delegate: TextInputLayout.AccessibilityDelegate) {
    if (editText != null) {
      ViewCompat.setAccessibilityDelegate(editText, delegate)
    }
  }

  init {
    shapeAppearanceModel =
      ShapeAppearanceModel(context, attrs, defStyleAttr, DEF_STYLE_RES)
    cornerAdjustedShapeAppearanceModel =
      ShapeAppearanceModel(shapeAppearanceModel)

    collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.LINEAR_INTERPOLATOR)
    collapsingTextHelper.setPositionInterpolator(AnimationUtils.LINEAR_INTERPOLATOR)
    collapsingTextHelper.collapsedTextGravity = Gravity.TOP or GravityCompat.START

    inputFrame = FrameLayout(context)
    inputFrame.setAddStatesFromChildren(true)
    addView(inputFrame)



    tintTypedArray(context, defStyleAttr)?.run {
      box()
      hint()
      deafultColor()
      boxStrokeColor(context)
      boxBackground(context)
//      startIcon(context)
    }
  }

  private fun TintTypedArray.boxBackground(context: Context) {
    val filledBackgroundColorStateList =
      MaterialResources.getColorStateList(context, this, R.styleable.TextInputLayout_boxBackgroundColor)
    if (filledBackgroundColorStateList != null) {
      defaultFilledBackgroundColor = filledBackgroundColorStateList!!.getDefaultColor()
      boxBackgroundColor = defaultFilledBackgroundColor
      if (filledBackgroundColorStateList!!.isStateful()) {
        disabledFilledBackgroundColor = filledBackgroundColorStateList!!.getColorForState(
          intArrayOf(-android.R.attr.state_enabled), -1
        )
        hoveredFilledBackgroundColor = filledBackgroundColorStateList!!.getColorForState(
          intArrayOf(android.R.attr.state_hovered), -1
        )
      } else {
        val mtrlFilledBackgroundColorStateList =
          AppCompatResources.getColorStateList(context, R.color.mtrl_filled_background_color)
        disabledFilledBackgroundColor = mtrlFilledBackgroundColorStateList.getColorForState(
          intArrayOf(-android.R.attr.state_enabled), -1
        )
        hoveredFilledBackgroundColor = mtrlFilledBackgroundColorStateList.getColorForState(
          intArrayOf(android.R.attr.state_hovered), -1
        )
      }
    } else {
      boxBackgroundColor = Color.TRANSPARENT
      defaultFilledBackgroundColor = Color.TRANSPARENT
      disabledFilledBackgroundColor = Color.TRANSPARENT
      hoveredFilledBackgroundColor = Color.TRANSPARENT
    }
  }

  @SuppressLint("RestrictedApi")
  private fun TintTypedArray.boxStrokeColor(context: Context) {
    val boxStrokeColorStateList =
      MaterialResources.getColorStateList(context, this, R.styleable.TextInputLayout_boxStrokeColor)
    if (boxStrokeColorStateList != null && boxStrokeColorStateList!!.isStateful()) {
      defaultStrokeColor = boxStrokeColorStateList!!.getDefaultColor()
      disabledColor =
        boxStrokeColorStateList!!.getColorForState(intArrayOf(-android.R.attr.state_enabled), -1)
      hoveredStrokeColor =
        boxStrokeColorStateList!!.getColorForState(intArrayOf(android.R.attr.state_hovered), -1)
      focusedStrokeColor =
        boxStrokeColorStateList!!.getColorForState(intArrayOf(android.R.attr.state_focused), -1)
    } else {
      // If attribute boxStrokeColor is not a color state list but only a single value, its value
      // will be applied to the box's focus state.
      focusedStrokeColor = getColor(R.styleable.TextInputLayout_boxStrokeColor, Color.TRANSPARENT)
      defaultStrokeColor =
        ContextCompat.getColor(context, R.color.mtrl_textinput_default_box_stroke_color)
      disabledColor = ContextCompat.getColor(context, R.color.mtrl_textinput_disabled_color)
      hoveredStrokeColor =
        ContextCompat.getColor(context, R.color.mtrl_textinput_hovered_box_stroke_color)
    }
  }

  @SuppressLint("RestrictedApi")
  private fun TintTypedArray.deafultColor() {
    if (hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
      focusedTextColor = getColorStateList(R.styleable.TextInputLayout_android_textColorHint)
      defaultHintTextColor = focusedTextColor
    }
  }

//  private fun TintTypedArray.startIcon(context: Context) {
//    // Initialize start icon view.
//    startIconView = LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_start_icon, inputFrame, false) as CheckableImageButton
//    inputFrame.addView(startIconView)
//    startIconView.visibility = View.GONE
//    setStartIconOnClickListener(null)
//    // Set up start icon if any.
//    if (hasValue(R.styleable.TextInputLayout_startIconDrawable)) {
//      setStartIconDrawable(getDrawable(R.styleable.TextInputLayout_startIconDrawable))
//      if (hasValue(R.styleable.TextInputLayout_startIconContentDescription)) {
//        setStartIconContentDescription(getText(R.styleable.TextInputLayout_startIconContentDescription))
//      }
//    }
//    // Default tint for a start icon or value specified by user.
//    if (hasValue(R.styleable.TextInputLayout_startIconTint)) {
//      setStartIconTintList(MaterialResources.getColorStateList(context, a, R.styleable.TextInputLayout_startIconTint))
//    }
//    // Default tint mode for a start icon or value specified by user.
//    if (hasValue(R.styleable.TextInputLayout_startIconTintMode)) {
//      setStartIconTintMode(ViewUtils.parseTintMode(getInt(R.styleable.TextInputLayout_startIconTintMode, -1), null))
//    }
//  }

  @SuppressLint("RestrictedApi")
  private fun TintTypedArray.hint() {
    hintEnabled = getBoolean(R.styleable.TextInputLayout_hintEnabled, true)
    setHint(getText(R.styleable.TextInputLayout_android_hint))
    hintAnimationEnabled = getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true)
  }

  fun setHint(hint: CharSequence?) {
    if (hintEnabled) {
      setHintInternal(hint)
      sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED)
    }
  }

  private fun setHintInternal(hint: CharSequence?) {
    if (!TextUtils.equals(hint, this.hint)) {
      this.hint = hint
      collapsingTextHelper.text = hint
      // Reset the cutout to make room for a larger hint.
      openCutout()
    }
  }

  @SuppressLint("RestrictedApi")
  private fun TintTypedArray.box() {
    val boxCornerRadiusTopStart =
      getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopStart, -1f)
    val boxCornerRadiusTopEnd = getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopEnd, -1f)
    val boxCornerRadiusBottomEnd =
      getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1f)
    val boxCornerRadiusBottomStart =
      getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomStart, -1f)

    boxLabelCutoutPaddingPx =
      context.resources.getDimensionPixelOffset(R.dimen.mtrl_textinput_box_label_cutout_padding)
    boxCollapsedPaddingTopPx =
      getDimensionPixelOffset(R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0)
    boxStrokeWidthDefaultPx =
      context.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_default)
    boxStrokeWidthFocusedPx =
      context.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_focused)
    boxStrokeWidthPx = boxStrokeWidthDefaultPx

    if (boxCornerRadiusTopStart >= 0) {
      shapeAppearanceModel.topLeftCorner.cornerSize = boxCornerRadiusTopStart
    }
    if (boxCornerRadiusTopEnd >= 0) {
      shapeAppearanceModel.topRightCorner.cornerSize = boxCornerRadiusTopEnd
    }
    if (boxCornerRadiusBottomEnd >= 0) {
      shapeAppearanceModel.bottomRightCorner.cornerSize = boxCornerRadiusBottomEnd
    }
    if (boxCornerRadiusBottomStart >= 0) {
      shapeAppearanceModel.bottomLeftCorner.cornerSize = boxCornerRadiusBottomStart
    }


    setBoxBackgroundMode(getInt(R.styleable.TextInputLayout_boxBackgroundMode, BOX_BACKGROUND_NONE))
    recycle()
  }

  fun setBoxBackgroundMode(@BoxBackgroundMode boxBackgroundMode: Int) {
    if (boxBackgroundMode == this.boxBackgroundMode) {
      return
    }
    this.boxBackgroundMode = boxBackgroundMode
    if (editText != null) {
      onApplyBoxBackgroundMode()
    }
  }

  private fun tintTypedArray(context: Context, defStyleAttr: Int): TintTypedArray? =
    ThemeEnforcement.obtainTintedStyledAttributes(
      context,
      attrs,
      R.styleable.TextInputLayout,
      defStyleAttr,
      DEF_STYLE_RES,
      R.styleable.TextInputLayout_counterTextAppearance,
      R.styleable.TextInputLayout_counterOverflowTextAppearance,
      R.styleable.TextInputLayout_errorTextAppearance,
      R.styleable.TextInputLayout_helperTextTextAppearance,
      R.styleable.TextInputLayout_hintTextAppearance
    )

  private fun onApplyBoxBackgroundMode() {
    assignBoxBackgroundByMode()
    setEditTextBoxBackground()
    updateTextInputBoxState()
    if (boxBackgroundMode != BOX_BACKGROUND_NONE) {
      updateInputLayoutMargins()
    }
  }

  private fun assignBoxBackgroundByMode() {
    when (boxBackgroundMode) {
      BOX_BACKGROUND_FILLED -> {
        boxBackground = MaterialShapeDrawable(shapeAppearanceModel)
        boxUnderline = MaterialShapeDrawable()
      }
      BOX_BACKGROUND_OUTLINE -> {
        if (hintEnabled && boxBackground !is CutoutDrawable) {
          boxBackground = CutoutDrawable(shapeAppearanceModel)
        } else {
          boxBackground = MaterialShapeDrawable(shapeAppearanceModel)
        }
        boxUnderline = null
      }
      BOX_BACKGROUND_NONE -> {
        boxBackground = null
        boxUnderline = null
      }
      else -> throw IllegalArgumentException("$boxBackgroundMode is illegal; only @BoxBackgroundMode constants are supported.")
    }
  }

  private fun setEditTextBoxBackground() {
    if (shouldUseEditTextBackgroundForBoxBackground()) {
      ViewCompat.setBackground(editText!!, boxBackground)
    }
  }

  private fun shouldUseEditTextBackgroundForBoxBackground(): Boolean =
    (editText != null
      && boxBackground != null
      && editText.background == null
      && boxBackgroundMode != BOX_BACKGROUND_NONE)


  internal fun updateTextInputBoxState() {
    if (boxBackground == null || boxBackgroundMode == BOX_BACKGROUND_NONE) {
      return
    }

    val hasFocus = isFocused || editText != null && editText.hasFocus()
    val isHovered = isHovered || editText != null && editText.isHovered

    // Update the text box's stroke color based on the current state.
    when {
      !isEnabled -> boxStrokeColor = disabledColor
//      indicatorViewController.errorShouldBeShown() -> boxStrokeColor =
//        indicatorViewController.getErrorViewCurrentTextColor()
//        counterOverflowed && counterView != null -> boxStrokeColor = counterView.getCurrentTextColor()
      hasFocus -> boxStrokeColor = focusedStrokeColor
      isHovered -> boxStrokeColor = hoveredStrokeColor
      else -> boxStrokeColor = defaultStrokeColor
    }

    // Update the text box's stroke width based on the current state.
    update(isHovered, hasFocus)

    // Update the text box's background color based on the current state.
    updateFilled(isHovered)

    applyBoxAttributes()
  }

  private fun updateFilled(isHovered: Boolean) {
    if (boxBackgroundMode == BOX_BACKGROUND_FILLED) {
      when {
        !isEnabled -> boxBackgroundColor = disabledFilledBackgroundColor
        isHovered -> boxBackgroundColor = hoveredFilledBackgroundColor
        else -> boxBackgroundColor = defaultFilledBackgroundColor
      }
    }
  }

  private fun update(isHovered: Boolean, hasFocus: Boolean) {
    if ((isHovered || hasFocus) && isEnabled) {
      boxStrokeWidthPx = boxStrokeWidthFocusedPx
      adjustCornerSizeForStrokeWidth()
    } else {
      boxStrokeWidthPx = boxStrokeWidthDefaultPx
      adjustCornerSizeForStrokeWidth()
    }
  }

  private fun adjustCornerSizeForStrokeWidth() {
    val strokeInset =
      (if (boxBackgroundMode == BOX_BACKGROUND_OUTLINE) boxStrokeWidthPx / 2 else 0).toFloat()
    if (strokeInset <= 0f) {
      return  // Only adjust the corner size if there's a stroke inset.
    }

    val cornerRadiusTopLeft = shapeAppearanceModel.topLeftCorner.cornerSize
    cornerAdjustedShapeAppearanceModel
      .topLeftCorner.cornerSize = cornerRadiusTopLeft + strokeInset

    val cornerRadiusTopRight = shapeAppearanceModel.topRightCorner.cornerSize
    cornerAdjustedShapeAppearanceModel
      .topRightCorner.cornerSize = cornerRadiusTopRight + strokeInset

    val cornerRadiusBottomRight = shapeAppearanceModel.bottomRightCorner.cornerSize
    cornerAdjustedShapeAppearanceModel
      .bottomRightCorner.cornerSize = cornerRadiusBottomRight + strokeInset

    val cornerRadiusBottomLeft = shapeAppearanceModel.bottomLeftCorner.cornerSize
    cornerAdjustedShapeAppearanceModel
      .bottomLeftCorner.cornerSize = cornerRadiusBottomLeft + strokeInset

    ensureCornerAdjustedShapeAppearanceModel()
  }

  private fun ensureCornerAdjustedShapeAppearanceModel() {
    if (boxBackgroundMode != BOX_BACKGROUND_NONE && getBoxBackground() is MaterialShapeDrawable) {
      (getBoxBackground() as MaterialShapeDrawable).shapeAppearanceModel =
        cornerAdjustedShapeAppearanceModel
    }
  }

  private fun getBoxBackground(): Drawable {
    if (boxBackgroundMode == BOX_BACKGROUND_FILLED || boxBackgroundMode == BOX_BACKGROUND_OUTLINE) {
      return boxBackground as Drawable
    }
    throw IllegalStateException()
  }

  private fun updateInputLayoutMargins() {
    if (boxBackgroundMode != BOX_BACKGROUND_FILLED) {
      val lp = inputFrame.getLayoutParams() as LinearLayout.LayoutParams
      val newTopMargin = calculateLabelMarginTop()

      if (newTopMargin != lp.topMargin) {
        lp.topMargin = newTopMargin
        inputFrame.requestLayout()
      }
    }
  }

  private fun calculateLabelMarginTop(): Int {
    if (!hintEnabled) {
      return 0
    }

    when (boxBackgroundMode) {
      BOX_BACKGROUND_OUTLINE -> return (collapsingTextHelper.getCollapsedTextHeight() / 2).toInt()
      BOX_BACKGROUND_FILLED, BOX_BACKGROUND_NONE -> return collapsingTextHelper.getCollapsedTextHeight().toInt()
      else -> return 0
    }
  }

  private fun applyBoxAttributes() {
    if (boxBackground == null) {
      return
    }

    if (canDrawOutlineStroke()) {
      boxBackground?.setStroke(boxStrokeWidthPx.toFloat(), boxStrokeColor)
    }

    boxBackground?.fillColor = ColorStateList.valueOf(calculateBoxBackgroundColor())
    applyBoxUnderlineAttributes()
    invalidate()
  }

  private fun calculateBoxBackgroundColor(): Int {
    var backgroundColor = boxBackgroundColor
    if (boxBackgroundMode == BOX_BACKGROUND_FILLED) {
      val surfaceLayerColor = MaterialColors.getColor(this, R.attr.colorSurface, Color.TRANSPARENT)
      backgroundColor = MaterialColors.layer(surfaceLayerColor, boxBackgroundColor)
    }
    return backgroundColor
  }

  private fun applyBoxUnderlineAttributes() {
    // Exit if the underline is not being drawn by TextInputLayout.
    if (boxUnderline == null) {
      return
    }

    if (canDrawStroke()) {
      boxUnderline?.fillColor = ColorStateList.valueOf(boxStrokeColor)
    }
    invalidate()
  }

  private fun canDrawOutlineStroke(): Boolean =
    boxBackgroundMode == BOX_BACKGROUND_OUTLINE && canDrawStroke()

  private fun canDrawStroke(): Boolean =
    boxStrokeWidthPx > -1 && boxStrokeColor != Color.TRANSPARENT

}
