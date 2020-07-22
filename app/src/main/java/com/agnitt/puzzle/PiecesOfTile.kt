package com.agnitt.puzzle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.graphics.createBitmap
import com.agnitt.puzzle.PiecesOfTile.Companion.pieces
import com.agnitt.puzzle.Tile.Companion.tile
import com.google.android.material.card.MaterialCardView


class PiecesOfTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : GridView(context, attrs, defStyleAttr, defStyleRes) {

    val childrens = mutableListOf<Piece>()

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            .apply { setPadding(30, 30, 30, 0) }
        background = resources.getDrawable(R.drawable.bottom_panel)
        elevation = 10f
        clipToPadding = false

        columnWidth = AUTO_FIT
        numColumns = 3
        horizontalSpacing = 30
        verticalSpacing = 30

        pieces = mutableListOf()
        cardPieces = mutableListOf()

        context.doWithAssetsList { imgFileName ->
            val stream = context.assets.open(imgFileName)
            pieces.add(BitmapFactory.decodeStream(stream))
            stream.close()
            val piece = Piece(context, pieces.last().crop(), pieces.lastIndex)
            cardPieces.add(piece)
            childrens.add(piece)
        }
        adapter = GridAdapter(context, cardPieces)
    }

    internal companion object {
        lateinit var pieces: MutableList<Bitmap>
        lateinit var cardPieces: MutableList<Piece>
    }
}
