package com.agnitt.puzzle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Tile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint: Paint = Paint()
    var checkedPieces: MutableList<Bitmap> = mutableListOf()

    init {
        tile = this
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val left = if (PiecesOfTile.pieces.isNotEmpty()) this.measuredWidth / 2 - PiecesOfTile.pieces[0].width / 2 else 0
        val top = if (PiecesOfTile.pieces.isNotEmpty()) this.measuredHeight / 2 - PiecesOfTile.pieces[0].height / 2 else 0
        checkedPieces.forEach { canvas?.drawBitmap(it, left.toFloat(), top.toFloat(), paint) }
        invalidate()
    }

    fun insertPiece(index: Int) = checkedPieces.add(PiecesOfTile.pieces[index])
    fun insertPieces(indexes: List<Int>) = indexes.forEach { checkedPieces.add(PiecesOfTile.pieces[it]) }

    fun deletePiece(index: Int) = checkedPieces.remove(PiecesOfTile.pieces[index])
    fun deletePieces(indexes: List<Int>) = indexes.forEach { checkedPieces.remove(PiecesOfTile.pieces[it]) }

    internal companion object {
        lateinit var tile: Tile
    }
}