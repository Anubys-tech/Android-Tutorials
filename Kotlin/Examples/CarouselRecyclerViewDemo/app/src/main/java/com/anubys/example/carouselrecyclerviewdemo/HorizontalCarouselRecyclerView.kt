package com.anubys.example.carouselrecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlin.math.pow


class HorizontalCarouselRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {
    private val tag = HorizontalCarouselRecyclerView::class.java.simpleName

    private var viewsToChangeColor: List<Int> = listOf()
    private val activeColor by lazy { ContextCompat.getColor(context, R.color.blue) }
    private val inactiveColor by lazy { ContextCompat.getColor(context, R.color.gray) }


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    fun <T: ViewHolder> initialize(newAdapter: Adapter<T>) {
        Log.d(tag,"TAG - HorizontalCarouselRecyclerView - initialize()")

        // Ein Layout erstellen (Horizontal)
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)

        // Einen Beobachter regestrieren, um auf Datenänderungen zu achten
        newAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                post {
                    val sidePadding = (width / 2) - (getChildAt(0).width / 2)
                    setPadding(sidePadding, 0, sidePadding, 0)
                    scrollToPosition(0)
                    addOnScrollListener(object : OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            onScrollChanged()
                        }
                    })
                }
            }
        })

        adapter = newAdapter
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun setViewsToChangeColor(viewIds: List<Int>) {
        Log.d(tag,"TAG - HorizontalCarouselRecyclerView - setViewsToChangeColor()")

        viewsToChangeColor = viewIds
    }

    private fun onScrollChanged() {
        post {
            (0 until childCount).forEach { position ->
                val child = getChildAt(position)
                val childCenterX = (child.left + child.right) / 2
                val scaleValue = getGaussianScale(childCenterX)
                child.scaleX = scaleValue
                child.scaleY = scaleValue
                colorView(child, scaleValue)
            }
        }
    }

    private fun colorView(child: View, scaleValue: Float) {
        val saturationPercent = (scaleValue - 1) / 1f
        val alphaPercent = scaleValue / 2f
        val matrix = ColorMatrix()
        matrix.setSaturation(saturationPercent)

        viewsToChangeColor.forEach { viewId ->
            when (val viewToChangeColor = child.findViewById<View>(viewId)) {
                is ImageView -> {
                    viewToChangeColor.colorFilter = ColorMatrixColorFilter(matrix)
                    viewToChangeColor.imageAlpha = (255 * alphaPercent).toInt()
                }
                is TextView -> {
                    val textColor = ArgbEvaluator().evaluate(saturationPercent, inactiveColor, activeColor) as Int
                    viewToChangeColor.setTextColor(textColor)
                }
            }
        }
    }

    private fun getGaussianScale(childCenterX: Int, minScaleOffset: Float = 1f, scaleFactor: Float = 1f, spreadFactor: Double = 150.toDouble()): Float {
        val recyclerCenterX = (left + right) / 2
        return (Math.E.pow(-(childCenterX - recyclerCenterX.toDouble()).pow(2.toDouble()) / (2 * spreadFactor.pow(2.toDouble()))) * scaleFactor + minScaleOffset).toFloat()
    }
}
