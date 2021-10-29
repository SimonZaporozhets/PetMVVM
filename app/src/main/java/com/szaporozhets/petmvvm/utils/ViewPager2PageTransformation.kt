package com.szaporozhets.petmvvm.utils

import android.content.Context
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import com.szaporozhets.petmvvm.R

class ViewPager2PageTransformation(val context: Context) : ViewPager2.PageTransformer {

    private val pageMarginPx = context.resources.getDimensionPixelOffset(R.dimen.pageMargin)
    private val offsetPx = context.resources.getDimensionPixelOffset(R.dimen.offset)

    override fun transformPage(page: View, position: Float) {
        when {
            position < -1 ->
                page.apply {
                    scaleX = 0.85f
                    scaleY = 0.85f
                }
            position <= 1 -> {
                page.apply {
                    scaleX = kotlin.math.max(0.85f, 1 - kotlin.math.abs(position))
                    scaleY = kotlin.math.max(0.85f, 1 - kotlin.math.abs(position))
                }
            }
            else -> page.apply {
                scaleX = 0.85f
                scaleY = 0.85f
            }
        }
        val vPager = page.parent.parent as ViewPager2
        val offset = position * -(2 * offsetPx + pageMarginPx)
        if (vPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (ViewCompat.getLayoutDirection(vPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                page.translationX = -offset
            } else {
                page.translationX = offset
            }
        } else {
            page.translationY = offset
        }
    }
}