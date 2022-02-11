package com.rabilu.drawingapp

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {

    companion object {
        val paint_brush = Paint()
        var paint_brush_color = Color.BLACK
        var path = Path()
        var drawing = ""
        const val FREE_HAND_DRAWING = "free hand drawing"
        const val ARROW_DRAWING = "arrow drawing"
        const val RECTANGLE = "rectangle"
        const val CIRCLE = "circle"
        var YOrigin = 0F
        var XOrigin = 0F

    }

    private lateinit var freeHandDrawing: ImageButton
    private lateinit var arrowDrawing: ImageButton
    private lateinit var rectangleDrawing: ImageButton
    private lateinit var circleDrawing: ImageButton
    private lateinit var colorSelection: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        freeHandDrawing = findViewById(R.id.free_hand_drawing)
        arrowDrawing = findViewById(R.id.arrow_drawing)
        rectangleDrawing = findViewById(R.id.rectangle_drawing)
        circleDrawing = findViewById(R.id.circle_drawing)
        colorSelection = findViewById(R.id.color_selection)
        val colorLayout: LinearLayout = findViewById(R.id.colorLayout)


        val black = findViewById<ImageView>(R.id.black_color)
        val blue = findViewById<ImageView>(R.id.blue_color)
        val green = findViewById<ImageView>(R.id.green_color)
        val red = findViewById<ImageView>(R.id.red_color)

        black.setOnClickListener {
            paint_brush_color = R.color.black
            colorLayout.visibility = View.GONE
        }

        blue.setOnClickListener {
            paint_brush_color = R.color.blue
            colorLayout.visibility = View.GONE
        }

        green.setOnClickListener {
            paint_brush_color = R.color.green
            colorLayout.visibility = View.GONE

        }

        red.setOnClickListener {
            paint_brush_color = R.color.red
            colorLayout.visibility = View.GONE
        }


        freeHandDrawing.setOnClickListener {
            drawing = FREE_HAND_DRAWING
            selectedDrawing(freeHandDrawing)
            deSelectDrawing(arrowDrawing)
            deSelectDrawing(rectangleDrawing)
            deSelectDrawing(circleDrawing)
        }

        arrowDrawing.setOnClickListener {
            drawing = ARROW_DRAWING
            selectedDrawing(arrowDrawing)
            deSelectDrawing(freeHandDrawing)
            deSelectDrawing(rectangleDrawing)
            deSelectDrawing(circleDrawing)
        }

        rectangleDrawing.setOnClickListener {
            drawing = RECTANGLE
            selectedDrawing(rectangleDrawing)
            deSelectDrawing(arrowDrawing)
            deSelectDrawing(freeHandDrawing)
            deSelectDrawing(circleDrawing)
        }

        circleDrawing.setOnClickListener {
            drawing = CIRCLE
            selectedDrawing(circleDrawing)
            deSelectDrawing(arrowDrawing)
            deSelectDrawing(rectangleDrawing)
            deSelectDrawing(freeHandDrawing)
        }

        colorSelection.setOnClickListener {
            if (!colorLayout.isVisible) {
                colorLayout.visibility = View.VISIBLE
            } else {
                colorLayout.visibility = View.GONE
            }
        }

    }

    private fun selectedDrawing(drawing: ImageButton) {
        drawing.setBackgroundColor(resources.getColor(R.color.white_ash))
    }

    private fun deSelectDrawing(drawing: ImageButton) {
        drawing.background = null
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("Axis", "onTouchEvent: X-axis${event.x} Y-axis${event.y}")
        YOrigin = event.y
        XOrigin = event.x
        return super.onTouchEvent(event)
    }

    override fun onPause() {
        super.onPause()
        YOrigin = 0F
        XOrigin = 0F
    }

    override fun onStop() {
        super.onStop()
        YOrigin = 0F
        XOrigin = 0F
    }
}