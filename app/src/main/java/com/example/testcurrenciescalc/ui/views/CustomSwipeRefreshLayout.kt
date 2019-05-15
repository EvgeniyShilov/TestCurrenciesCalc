package com.example.testcurrenciescalc.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class CustomSwipeRefreshLayout(context: Context, attrs: AttributeSet?) : SwipeRefreshLayout(context, attrs) {

    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    private var previousX = 0f

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val motionEvent = MotionEvent.obtain(event)
                previousX = motionEvent.x
                motionEvent.recycle()
            }
            MotionEvent.ACTION_MOVE -> if (Math.abs(event.x - previousX) > touchSlop) return false
        }
        return super.onInterceptTouchEvent(event)
    }
}
