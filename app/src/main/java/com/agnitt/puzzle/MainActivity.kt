package com.agnitt.puzzle

import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ListPopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.createBitmap
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menu_item_send -> sendPicture()
//            R.id.menu_item_clear -> uncheckedPieces()
//            R.id.menu_item_random -> checkedRandomPieces()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    fun sendPicture() = if (dbh.countVideos() != 0) this.startActivity(Intent.createChooser(Intent().apply {
//        action = Intent.ACTION_SEND
//        putExtra(Intent.EXTRA_TEXT, dbh.getAllVideos().toStringForShare())
//        type = "text/plain"
//    }, null))
//    else toast(this, "Ваша коллекция видео пуста Т_Т")
//    fun uncheckedPieces() {}
//    fun checkedRandomPieces() {}
}

//fun Context.clearBase() =
//    if (dbh.countVideos() == 0) toast(this, "Видеотека пуста")
//    else {
//        dbh.deleteAllVideos()
//        toast(this, "Видеотека очищена")
//    }