package com.agnitt.puzzle

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ListPopupWindow
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.createBitmap
import com.agnitt.puzzle.Tile.Companion.tile
import com.google.android.material.appbar.MaterialToolbar
import java.time.Duration


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_send -> sendPicture()
            R.id.menu_item_clear -> uncheckPieces()
            R.id.menu_item_random -> checkedRandomPieces()
        }
        return super.onOptionsItemSelected(item)
    }

    fun sendPicture() = if (tile.checkedPieces.isNotEmpty())
//        this@MainActivity.startActivity(Intent.createChooser(Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(Intent.
//                , tile.bitmap)
//            type = "text/plain"
//        }, null))
        null
    else Toast.makeText(this,"Кажется, делиться пока нечем (－.－)...zzz", Toast.LENGTH_SHORT).show()


    fun uncheckPieces() = findViewById<PiecesOfTile>(R.id.piecesOfTile).childrens
        .forEach { it.uncheck() }

    fun checkedRandomPieces() = findViewById<PiecesOfTile>(R.id.piecesOfTile).childrens
        .apply { forEach { it.uncheck() } }
        .getRandomPieces()
        .forEach { it.check() }
}