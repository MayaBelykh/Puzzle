package com.agnitt.puzzle.views

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.agnitt.puzzle.R
import com.google.android.material.card.MaterialCardView

class Piece @JvmOverloads constructor(
    context: Context,
    private val img: Bitmap? = null,
    private val position: Int = 0,
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
        setOnClickListener { onClick(this) }
        setOnLongClickListener { onClick(this) }
        setImg()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        setMeasuredDimension(width, width)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, w, oldw, oldh)
    }

    private fun setImg() = this.addView(ImageView(context).apply {
        tag = "main"
        setImageBitmap(img)
        setCardBackgroundColor(resources.getColor(R.color.card_background))
        layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER)
                .apply { setPadding(10, 10, 10, 10) }
    })

    private fun setIcon() = this.addView(ImageView(context).apply {
        tag = "icon"
        setImageResource(R.drawable.ic_baseline_check_24)
        setBackgroundColor(resources.getColor(R.color.black_semitransparent))
        val side = this@Piece.measuredWidth
        layoutParams = LayoutParams(side, side, Gravity.CENTER)
            .apply { setPadding(side / 3, side / 3, side / 3, side / 3) }
    })

    private fun onClick(view: View): Boolean {
        val checkedIcon = findViewWithTag<ImageView>("icon")
        tag = when (tag.toString().toBoolean()) {
            false -> {
                if (checkedIcon != null) checkedIcon.visibility = View.VISIBLE else setIcon()
                Tile.tile.insertPiece(this@Piece.position)
                true
            }
            true -> {
                if (checkedIcon != null) checkedIcon.visibility = View.INVISIBLE
                Tile.tile.deletePiece(this@Piece.position)
                false
            }
        }
        return true
    }

    fun uncheck() = if (tag.toString().toBoolean()) onClick(this) else null
    fun check() = if (!tag.toString().toBoolean()) onClick(this) else null
}