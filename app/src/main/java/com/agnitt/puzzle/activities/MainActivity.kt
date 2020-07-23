package com.agnitt.puzzle.activities

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agnitt.puzzle.R
import com.agnitt.puzzle.utils.getRandomPieces
import com.agnitt.puzzle.views.PiecesOfTile.Companion.piecesOfTile
import com.agnitt.puzzle.views.Tile.Companion.tile
import com.google.android.material.appbar.MaterialToolbar

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
            R.id.menu_item_send -> sendPicture(tile.bitmap)
            R.id.menu_item_clear -> uncheckPieces()
            R.id.menu_item_random -> checkedRandomPieces()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sendPicture(btmp: Bitmap?) {
        if (btmp != null) {
            val path: String =
                MediaStore.Images.Media.insertImage(this.contentResolver, btmp, "puzzle", null)
            val uri = Uri.parse(path)
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "(⊃｡•́‿•̀｡)⊃--☆ ･:*:･ﾟ’★,｡･:*:･ﾟ’☆ <3")
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/png"
                startActivity(Intent.createChooser(this, "send"))
            }
        } else Toast.makeText(this, "Кажется, делиться пока нечем \n(－.－)...zzZ", Toast.LENGTH_SHORT)
            .show()
    }

    private fun uncheckPieces() = piecesOfTile.children
        .forEach { it.uncheck() }

    private fun checkedRandomPieces() = piecesOfTile.children
        .apply { forEach { it.uncheck() } }
        .getRandomPieces()
        .forEach { it.check() }
}