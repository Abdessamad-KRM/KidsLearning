package com.example.kids_learning

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.kids_learning.adapters.LanguageItem
import com.example.kids_learning.adapters.LangueAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            LanguageItem("Français", R.drawable.a, R.color.french_card_color),
            LanguageItem("العربية", R.drawable.alif, R.color.arabic_card_color)
        )
        val gridView = findViewById<ListView>(R.id.gridView)
        val adapter = LangueAdapter(this, items)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            val intent = Intent(this, LetterListActivity::class.java)
            val language = when (position) {
                0 -> "french"
                1 -> "arabic"
                else -> "french"
            }
            intent.putExtra("language", language)
            startActivity(intent)
        }
    }
}