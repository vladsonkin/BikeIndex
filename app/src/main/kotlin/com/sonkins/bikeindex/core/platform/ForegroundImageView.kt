/*
 * Copyright (c) 2018. Vlad Sonkin Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sonkins.bikeindex.core.platform

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.sonkins.bikeindex.R

/**
 * An ImageView which supports a foreground drawable for android lower than lollipop
 * https://gist.github.com/JakeWharton/0a251d67649305d84e8a
 */
class ForegroundImageView constructor(context: Context, attrs: AttributeSet? = null) :
    ImageView(context, attrs) {

    private var foregroundDrawable: Drawable? = null

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.ForegroundImageView)
        val foreground = attr.getDrawable(R.styleable.ForegroundImageView_android_foreground)
        if (foreground != null) {
            setForeground(foreground)
        }
        attr.recycle()
    }

    /**
     * Supply a drawable resource that is to be rendered on top of all of the child
     * views in the frame layout.
     *
     * @param drawableResId The drawable resource to be drawn on top of the children.
     */
    fun setForegroundResource(drawableResId: Int) {
        setForeground(ContextCompat.getDrawable(context, drawableResId))
    }

    /**
     * Supply a Drawable that is to be rendered on top of all of the child
     * views in the frame layout.
     *
     * @param drawable The Drawable to be drawn on top of the children.
     */
    override fun setForeground(drawable: Drawable?) {
        if (foregroundDrawable === drawable) {
            return
        }
        if (foregroundDrawable != null) {
            foregroundDrawable!!.callback = null
            unscheduleDrawable(foregroundDrawable)
        }

        foregroundDrawable = drawable

        if (drawable != null) {
            drawable.callback = this
            if (drawable.isStateful) {
                drawable.state = drawableState
            }
        }
        requestLayout()
        invalidate()
    }

    override fun verifyDrawable(who: Drawable): Boolean {
        return super.verifyDrawable(who) || who === foregroundDrawable
    }

    override fun jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState()
        if (foregroundDrawable != null) foregroundDrawable!!.jumpToCurrentState()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (foregroundDrawable != null && foregroundDrawable!!.isStateful) {
            foregroundDrawable!!.state = drawableState
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (foregroundDrawable != null) {
            foregroundDrawable!!.setBounds(0, 0, measuredWidth, measuredHeight)
            invalidate()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (foregroundDrawable != null) {
            foregroundDrawable!!.setBounds(0, 0, w, h)
            invalidate()
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (foregroundDrawable != null) {
            foregroundDrawable!!.draw(canvas)
        }
    }
}