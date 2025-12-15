package com.example.kids_learning.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.kids_learning.R

class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.drawing_color)
        strokeWidth = 78f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
    }

    private val letterPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.letter_background)
        textSize = 1500f
        textAlign = Paint.Align.CENTER
        alpha = 120 // Semi-transparent
        isAntiAlias = true
        typeface = Typeface.DEFAULT_BOLD
    }

    private val path = Path()
    private val paths = mutableListOf<Path>()
    private var letter = ""

    fun setLetter(letter: String) {
        this.letter = letter
        invalidate()
    }

    fun hasDrawing(): Boolean = paths.isNotEmpty()

    fun undo() {
        if (paths.isNotEmpty()) {
            paths.removeAt(paths.size - 1)
            invalidate()
        }
    }

    fun clear() {
        paths.clear()
        path.reset()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dessiner la lettre en arrière-plan (semi-transparente)
        if (letter.isNotEmpty()) {
            val centerX = width / 2f
            val centerY = height / 2f + letterPaint.textSize / 4
            canvas.drawText(letter, centerX, centerY, letterPaint)
        }

        // Dessiner tous les tracés
        for (drawPath in paths) {
            canvas.drawPath(drawPath, paint)
        }

        // Dessiner le tracé actuel
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                paths.add(Path(path))
                path.reset()
                invalidate()
            }
        }
        return true
    }
}