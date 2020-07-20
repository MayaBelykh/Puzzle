package com.agnitt.puzzle

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import androidx.cardview.widget.CardView

class PiecesOfTile(context: Context) : GridView(context) {
    init {
        pieces = mutableListOf<Drawable>().apply {
            for (i in 1..7) pieces.add(
                Drawable.createFromStream(context.assets.open("$i.png"), null)
            )
        }
//        var size = pieces.size
    }

    internal companion object {
        lateinit var pieces: MutableList<Drawable>
    }
}

class Piece(context: Context, val img: Drawable) : CardView(context) {

    init {
        radius = resources.getDimension(R.dimen.corner_radius)
        setCardBackgroundColor(resources.getColor(R.color.card_background))
        addView(ImageView(context).apply { setImageDrawable(img) })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        setMeasuredDimension(width, width)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, w, oldw, oldh)
    }
}

class Tile(context: Context, val list: List<Int>) : View(context) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        list.forEach {

        }
    }
}