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
import com.google.android.material.card.MaterialCardView


class PiecesOfTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : GridView(context, attrs, defStyleAttr, defStyleRes) {
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
            cardPieces.add(Piece(context, pieces.last().crop(), pieces.lastIndex))
        }
        adapter = GridAdapter(context, cardPieces)
    }

    internal companion object {
        lateinit var pieces: MutableList<Bitmap>
        lateinit var cardPieces: MutableList<Piece>
    }
}

class GridAdapter(private val context: Context, private val pieces: List<Piece>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
        (convertView ?: pieces[position])

    override fun getItem(position: Int): Any = pieces[position]
    override fun getItemId(position: Int): Long = pieces[position].id.toLong()
    override fun getCount(): Int = pieces.count()
}

class Piece @JvmOverloads constructor(
    context: Context,
    val img: Bitmap? = null,
    val position: Int = 0,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        id = View.generateViewId()
        tag = false
        layoutParams =
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.CENTER)
                .apply { setMargins(10, 10, 10, 10) }
        elevation = 10f
        radius = resources.getDimension(R.dimen.corner_radius)
        setCardBackgroundColor(resources.getColor(R.color.card_background))
        setOnClickListener { view -> onClick(view) }
        setImg()
    }

    fun setImg() = this.addView(ImageView(context).apply {
        tag = "main"
        setImageBitmap(img)
        setCardBackgroundColor(resources.getColor(R.color.card_background))
        layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER)
                .apply { setPadding(10, 10, 10, 10) }
    })

    fun setIcon() = this.addView(ImageView(context).apply {
        tag = "icon"
        setImageResource(R.drawable.ic_baseline_check_24)
        setBackgroundColor(resources.getColor(R.color.black_semitransparent))
        val side = this@Piece.measuredWidth
        layoutParams = LayoutParams(side, side, Gravity.CENTER)
            .apply { setPadding(side / 3, side / 3, side / 3, side / 3) }
    })

    fun onClick(view: View) = view.apply {
        val checkedIcon = this.findViewWithTag<ImageView>("icon")
        tag = when (tag.toString().toBoolean()) {
            false -> {
                if (checkedIcon != null) checkedIcon.visibility = View.VISIBLE else setIcon()
                true
            }
            true -> {
                if (checkedIcon != null) checkedIcon.visibility = View.INVISIBLE
                false
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        setMeasuredDimension(width, width)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, w, oldw, oldh)
    }
}

class Tile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var picture: Bitmap = lazy { createBitmap(pieces[0].width, pieces[0].height) }.value
    val paint: Paint = Paint()
    var checkedPieces: MutableList<Bitmap> = mutableListOf()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        checkedPieces.forEach {
            canvas?.drawBitmap(it, pieces[0].width.toFloat(), pieces[0].height.toFloat(), paint)
        }
    }

    fun insertPiece(index: Int) = checkedPieces.add(pieces[index])
    fun insertPieces(indexes: Int) = checkedPieces.add(pieces[index])

}