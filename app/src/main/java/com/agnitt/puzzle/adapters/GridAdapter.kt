package com.agnitt.puzzle.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.agnitt.puzzle.views.Piece

internal class GridAdapter(private val context: Context, private val pieces: List<Piece>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
        (convertView ?: pieces[position])
    override fun getItem(position: Int): Any = pieces[position]
    override fun getItemId(position: Int): Long = pieces[position].id.toLong()
    override fun getCount(): Int = pieces.count()
}