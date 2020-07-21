package com.agnitt.puzzle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.card.MaterialCardView


class PiecesOfTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : GridView(context, attrs, defStyleAttr, defStyleRes) {
    init {
        layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        ).apply {
            leftMargin = 5
            rightMargin = 5
            topMargin = 10
        }
        background = resources.getDrawable(R.drawable.bottom_panel)

        columnWidth = AUTO_FIT
        numColumns = 3

        pieces = mutableListOf()
        cropPieces = mutableListOf()
        cardPieces = mutableListOf()

        context.doWithAssetsList {
//            Log.d("LOG", it)
//            val stream = context.assets.open(it)
//            pieces.add(Drawable.createFromStream(context.assets.open(it), null))
//            cropPieces.add(
//                BitmapFactory.decodeStream(context.assets.open(it))
////                .crop()
//            )
            cardPieces.add(Piece(context,BitmapFactory.decodeStream(context.assets.open(it))))
        }
//        cropPieces.forEach { cardPieces.add(Piece(context, it)) }
        adapter = GridAdapter(context, cardPieces)
    }

    internal companion object {
        lateinit var pieces: MutableList<Drawable>
        lateinit var cropPieces: MutableList<Bitmap>
        lateinit var cardPieces: MutableList<Piece>
    }
}

class GridAdapter(private val context: Context, private val pieces: List<Piece>) :
    BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
        (if (convertView != null) convertView as Piece else pieces[position])
            .apply { tag = position }

    override fun getItem(position: Int): Any = pieces[position]
    override fun getItemId(position: Int): Long = pieces[position].id.toLong()
    override fun getCount(): Int = pieces.count()
}

class Piece @JvmOverloads constructor(
    context: Context,
    val img: Bitmap? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {
    init {
        id = View.generateViewId()
        radius = resources.getDimension(R.dimen.corner_radius)
        val margin = R.dimen.bottom_panel_card_margin
        val padding = R.dimen.bottom_panel_margin_horizontal
        setCardBackgroundColor(resources.getColor(R.color.card_background))
//        setPadding(padding, padding, padding, padding)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            .apply {
//                setMargins(margin, margin, margin, margin)
                gravity = Gravity.CENTER
            }
//        requestLayout()
        addView(ImageView(context).apply {
            setImageBitmap(img)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        setMeasuredDimension(width, width)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, w, oldw, oldh)
    }
}

class Tile @JvmOverloads constructor(
    context: Context,
    val list: List<Int> = listOf(),
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        list.forEach {

        }
    }
}