package com.agnitt.puzzle.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import com.agnitt.puzzle.views.Piece
import kotlin.random.Random

internal fun Context.doWithAssetsList(action: (String) -> Unit) =
    assets.list("")?.filter { it.contains(".png") }?.forEach(action)

internal fun Bitmap.crop(): Bitmap {
    var y1 = 0
    var y2 = 0
    var x1 = 0
    var x2 = 0

    getImgCoordinates(height, width, width, 0, null, width, 1).apply {
        y1 = this[0]
        y2 = this[1]
    }

    getImgCoordinates(width, y2 - y1, 1, null, y1, 1, y2 - y1).apply {
        x1 = this[0]
        x2 = this[1]
    }

    return Bitmap.createBitmap(this, x1, y1, x2 - x1, y2 - y1)
}

private fun Bitmap.getImgCoordinates(
    value: Int, num: Int, stride: Int, x: Int?, y: Int?, w: Int, h: Int
) = IntArray(2).apply {
    for (i in 0 until value)
        if (isNotTransparent(num, stride, x ?: i, y ?: i, w, h)) {
            this[0] = i
            break
        }
    for (i in value - 1 downTo this[0])
        if (isNotTransparent(num, stride, x ?: i, y ?: i, w, h)) {
            this[1] = i
            break
        }
}

private fun Bitmap.isNotTransparent(num: Int, stride: Int, x: Int, y: Int, w: Int, h: Int) =
    IntArray(num).let { pixels ->
        getPixels(pixels, 0, stride, x, y, w, h)
        !pixels.contentEquals(IntArray(num) { Color.TRANSPARENT })
    }

internal fun MutableList<Piece>.getRandomPieces() = mutableListOf<Piece>().also { resList ->
    for (i in 0..Random.nextInt(1, size)) resList.add(this[Random.nextInt(0, size)])
}.distinct()