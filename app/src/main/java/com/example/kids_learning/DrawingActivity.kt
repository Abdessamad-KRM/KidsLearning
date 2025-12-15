package com.example.kids_learning

import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kids_learning.data.ProgressManager
import com.example.kids_learning.views.DrawingView

class DrawingActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView
    private lateinit var progressManager: ProgressManager
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var letterId: String
    private lateinit var character: String
    private lateinit var language: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing)

        letterId = intent.getStringExtra("letterId") ?: ""
        character = intent.getStringExtra("character") ?: ""
        language = intent.getStringExtra("language") ?: "french"

        progressManager = ProgressManager(this)
        drawingView = findViewById(R.id.drawingView)
        
        drawingView.setLetter(character)

        val btnValidate = findViewById<ImageButton>(R.id.btnValidate)
        val btnSound = findViewById<ImageButton>(R.id.btnSound)
        val btnUndo = findViewById<Button>(R.id.btnUndo)
        val btnClear = findViewById<ImageButton>(R.id.btnClear)

        btnValidate.setOnClickListener { validateDrawing() }
        btnSound.setOnClickListener { playLetterSound() }
        btnUndo.setOnClickListener { drawingView.undo() }
        btnClear.setOnClickListener { drawingView.clear() }

        supportActionBar?.title = "Tracer: $character"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun validateDrawing() {
        // Simulation de validation - dans une vraie app, on analyserait le tracé
        val hasDrawing = drawingView.hasDrawing()
        
        if (hasDrawing) {
            // Simulation: 70% de chance de réussir
            val isValid = (0..100).random() > 30
            if (isValid) {
                progressManager.saveProgress(letterId, com.example.kids_learning.data.LetterStatus.COMPLETED)
                showSuccessDialog()
            } else {
                progressManager.saveProgress(letterId, com.example.kids_learning.data.LetterStatus.FAILED)
                showFailDialog()
            }
        } else {
            showTryAgainDialog()
        }
    }

    private fun showSuccessDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_success, null)
        val tvTitle = dialogView.findViewById<TextView>(R.id.tvTitle)
        val tvMessage = dialogView.findViewById<TextView>(R.id.tvMessage)
        val btnOk = dialogView.findViewById<Button>(R.id.btnOk)
        
        tvTitle.text = if (language == "french") "Bravo!" else "أحسنت!"
        tvMessage.text = if (language == "french") "Très bien tracé!" else "رسم ممتاز!"
        
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
            
        btnOk.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        
        dialog.show()
    }

    private fun showFailDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_fail, null)
        val tvTitle = dialogView.findViewById<TextView>(R.id.tvTitle)
        val tvMessage = dialogView.findViewById<TextView>(R.id.tvMessage)
        val btnOk = dialogView.findViewById<Button>(R.id.btnOk)
        
        tvTitle.text = if (language == "french") "Pas tout à fait!" else "حاول مرة أخرى!"
        tvMessage.text = if (language == "french") "Continue à t'entraîner" else "استمر في التدريب"
        
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
            
        btnOk.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        
        dialog.show()
    }
    
    private fun showTryAgainDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_fail, null)
        val tvTitle = dialogView.findViewById<TextView>(R.id.tvTitle)
        val tvMessage = dialogView.findViewById<TextView>(R.id.tvMessage)
        val btnOk = dialogView.findViewById<Button>(R.id.btnOk)
        
        tvTitle.text = if (language == "french") "Essaie encore!" else "حاول مرة أخرى!"
        tvMessage.text = if (language == "french") "Trace la lettre pour continuer" else "ارسم الحرف للمتابعة"
        
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
            
        btnOk.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }

    private fun playLetterSound() {
        try {
            mediaPlayer?.release()
            
            // Construire le nom du fichier audio
            val soundFileName = if (language == "french") {
                "fr_${character.lowercase()}"
            } else {
                // Pour l'arabe, utiliser le nom de l'image
                val letter = progressManager.loadLetters().find { it.id == letterId }
                "ar_${letter?.imageResource ?: "alif"}"
            }
            
            // Charger le fichier depuis res/raw
            val resourceId = resources.getIdentifier(soundFileName, "raw", packageName)
            if (resourceId != 0) {
                mediaPlayer = MediaPlayer.create(this, resourceId)
                mediaPlayer?.start()
            }
        } catch (e: Exception) {
            // Ignorer les erreurs si le fichier n'existe pas
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}