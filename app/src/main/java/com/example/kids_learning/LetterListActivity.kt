package com.example.kids_learning

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kids_learning.adapters.LetterAdapter
import com.example.kids_learning.data.ProgressManager

class LetterListActivity : AppCompatActivity() {
    private lateinit var progressManager: ProgressManager
    private lateinit var letterAdapter: LetterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_list)

        val language = intent.getStringExtra("language") ?: "french"
        progressManager = ProgressManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLetters)
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        
        // Configuration RTL pour l'arabe
        if (language == "arabic") {
            recyclerView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }

        val letters = progressManager.loadLetters().filter { it.language == language }
        
        letterAdapter = LetterAdapter(letters) { letter ->
            val intent = Intent(this, DrawingActivity::class.java)
            intent.putExtra("letterId", letter.id)
            intent.putExtra("character", letter.character)
            intent.putExtra("language", letter.language)
            startActivity(intent)
        }
        
        recyclerView.adapter = letterAdapter
        
        supportActionBar?.title = if (language == "french") "Lettres Françaises" else "الحروف العربية"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        letterAdapter.notifyDataSetChanged()
    }
}