package com.apelgigit.commons.customUI

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class CustomSwipeToRefresh
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs) {

    private val touchSlop: Int = android.view.ViewConfiguration.get(context).scaledTouchSlop
    private var startX = 0f
    private var startY = 0f
    private var forbidSwipe = false
    private var isStartScrolledByY = false

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val isScrolledByX = kotlin.math.abs(event.x - startX) > touchSlop
                val isScrolledByY = kotlin.math.abs(event.y - startY) > touchSlop
                if (!forbidSwipe && isScrolledByY) {
                    isStartScrolledByY = true
                }
                if ((isScrolledByX || forbidSwipe) && !isStartScrolledByY) {
                    forbidSwipe = true
                    return false
                }
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                forbidSwipe = false
                isStartScrolledByY = false
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        if (forbidSwipe) return
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }

}