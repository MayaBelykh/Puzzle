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
    val bitmap: Bitmap?
        get() = if (PiecesOfTile.pieces.isNotEmpty() && checkedPieces.isNotEmpty()) {
            val pieceForMeasure = PiecesOfTile.pieces[1]
            val btmp = Bitmap.createBitmap(
                pieceForMeasure.width,
                pieceForMeasure.height,
                Bitmap.Config.ARGB_8888
            )
            Canvas(btmp).putPieces()
            btmp
        } else null

    init {
        tile = this
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.putPieces()
        invalidate()
    }

    fun insertPiece(index: Int) = checkedPieces.add(PiecesOfTile.pieces[index])
    fun insertPieces(indexes: List<Int>) =
        indexes.forEach { checkedPieces.add(PiecesOfTile.pieces[it]) }

    fun deletePiece(index: Int) = checkedPieces.remove(PiecesOfTile.pieces[index])
    fun deletePieces(indexes: List<Int>) =
        indexes.forEach { checkedPieces.remove(PiecesOfTile.pieces[it]) }

    fun Canvas.putPieces() {
        val left = if (PiecesOfTile.pieces.isNotEmpty())
            this@Tile.measuredWidth / 2 - PiecesOfTile.pieces[1].width / 2 else 0
        val top = if (PiecesOfTile.pieces.isNotEmpty())
            this@Tile.measuredHeight / 2 - PiecesOfTile.pieces[1].height / 2 else 0
        checkedPieces.forEach { this.drawBitmap(it, left.toFloat(), top.toFloat(), paint) }
    }

    internal companion object {
        lateinit var tile: Tile
    }
}
