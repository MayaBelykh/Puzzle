package com.agnitt.puzzle.views

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.widget.GridView
import com.agnitt.puzzle.adapters.GridAdapter
import com.agnitt.puzzle.R
import com.agnitt.puzzle.utils.crop
import com.agnitt.puzzle.utils.doWithAssetsList

class PiecesOfTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : GridView(context, attrs, defStyleAttr, defStyleRes) {

    val children = mutableListOf<Piece>()

    init {
        piecesOfTile = this
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
            var btmp = BitmapFactory.decodeStream(stream)
            pieces.add(btmp)
            stream.close()

            val coeff = btmp.width / (Resources.getSystem().displayMetrics.widthPixels / 3)
            if (coeff > 1) btmp =
                Bitmap.createScaledBitmap(btmp, btmp.width * coeff, btmp.height * coeff, false)
            val piece = Piece(
                context,
                btmp.crop(),
                pieces.lastIndex
            )
            cardPieces.add(piece)
            children.add(piece)
        }
        adapter = GridAdapter(
            context,
            cardPieces
        )
    }

    internal companion object {
        lateinit var piecesOfTile: PiecesOfTile
        lateinit var pieces: MutableList<Bitmap>
        lateinit var cardPieces: MutableList<Piece>
    }
}