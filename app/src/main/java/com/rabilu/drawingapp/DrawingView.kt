package com.rabilu.drawingapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.rabilu.drawingapp.MainActivity.Companion.ARROW_DRAWING
import com.rabilu.drawingapp.MainActivity.Companion.CIRCLE
import com.rabilu.drawingapp.MainActivity.Companion.FREE_HAND_DRAWING
import com.rabilu.drawingapp.MainActivity.Companion.RECTANGLE
import com.rabilu.drawingapp.MainActivity.Companion.XOrigin
import com.rabilu.drawingapp.MainActivity.Companion.YOrigin
import com.rabilu.drawingapp.MainActivity.Companion.drawing
import com.rabilu.drawingapp.MainActivity.Companion.paint_brush
import com.rabilu.drawingapp.MainActivity.Companion.paint_brush_color
import com.rabilu.drawingapp.MainActivity.Companion.path


class DrawingView : View {

    constructor(context: Context?) : super(context) {
        intialise()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        intialise()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        intialise()
    }

    val pathList = ArrayList<Path>()
    private lateinit var params: ViewGroup.LayoutParams

    companion object {
        var rect: RectF? = RectF()
    }

    fun intialise() {
        paint_brush.isAntiAlias = true
        paint_brush.color = Color.BLACK
        paint_brush.style = Paint.Style.STROKE
        paint_brush.strokeCap = Paint.Cap.ROUND
        paint_brush.strokeJoin = Paint.Join.ROUND
        paint_brush.strokeWidth = 2f
        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        paint_brush.color = paint_brush_color

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {

                when (drawing) {
                    FREE_HAND_DRAWING -> {
                        path.lineTo(x, y)
                        pathList.add(path)
                    }

                    RECTANGLE -> {
                        rect = RectF(XOrigin, YOrigin, x, y)
                        path.addRect(rect!!, Path.Direction.CCW)
                        pathList.add(path)
                    }

                    CIRCLE -> {
                        path.addCircle(x, y, 50F, Path.Direction.CCW)
                        pathList.add(path)
                    }

                    ARROW_DRAWING -> {

                    }
                }
                invalidate()
                return true
            }
            else -> {
                return false
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        when (drawing) {
            FREE_HAND_DRAWING -> {
                var i = 0
                while (i < pathList.size) {
                    canvas.restore()
                    canvas.drawPath(pathList[i], paint_brush)
                    i += 1
                    invalidate()
                }
            }
            RECTANGLE -> {

                var i = 0
                while (i < pathList.size) {
                    canvas.restore()
                    canvas.drawPath(path, paint_brush)
                    i += 1
                    invalidate()

                }
            }

            CIRCLE -> {
                var i = 0
                while (i < pathList.size) {
                    canvas.restore()
                    canvas.drawPath(path, paint_brush)
                    i += 1
                    invalidate()

                }
            }
        }

    }
}

