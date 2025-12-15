package com.example.kids_learning

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val frenchCard = findViewById<CardView>(R.id.cardFrench)
        val arabicCard = findViewById<CardView>(R.id.cardArabic)
        
        frenchCard.setOnClickListener {
            val intent = Intent(this, LetterListActivity::class.java)
            intent.putExtra("language", "french")
            startActivity(intent)
        }
        
        arabicCard.setOnClickListener {
            val intent = Intent(this, LetterListActivity::class.java)
            intent.putExtra("language", "arabic")
            startActivity(intent)
        }
    }
}