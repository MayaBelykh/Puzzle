package com.agnitt.puzzle

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun assemblePuzzle() {

    }

    fun setAssets() {
//        findViewById<TableLayout>(R.id.tableLayout).apply {
//            val list = mutableListOf<Drawable>()
//            for (i in 1..7) list.add(Drawable.createFromStream(context.assets.open("$j.png"), null))
//            var size = list.size
//            for (i in 0 .. size / 3){
//                val row = TableRow(context)
//                for (j in 0 until 3) {
//                    val card = CardView(context).apply {
//                        radius = resources.getDimension(R.dimen.corner_radius)
//                        setCardBackgroundColor(resources.getColor(R.color.purple_700))
//                        val imageView= ImageView(context).apply {
//                            setImageDrawable(list[i])
//                        }
//                        addView()
//                    }
//                    row.addView(card)
//                }
//            }
//        }
    }
}