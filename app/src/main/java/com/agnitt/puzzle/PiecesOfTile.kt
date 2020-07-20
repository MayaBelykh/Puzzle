package com.agnitt.puzzle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import androidx.cardview.widget.CardView

class PiecesOfTile(context: Context) : GridView(context) {
    init {
        pieces = mutableListOf()
        cropPieces = mutableListOf()
        context.doWithAssetsList {
            val stream = context.assets.open(it)
            pieces.add(Drawable.createFromStream(stream, null))
            cropPieces.add(BitmapFactory.decodeStream(stream).crop())
        }
    }

    internal companion object {
        lateinit var pieces: MutableList<Drawable>
        lateinit var cropPieces: MutableList<Bitmap>
    }
}


class Piece(context: Context, val img: Drawable) : CardView(context) {

    init {
        id = View.generateViewId()
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