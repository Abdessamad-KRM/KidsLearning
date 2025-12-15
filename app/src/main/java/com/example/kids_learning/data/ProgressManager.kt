package com.example.kids_learning.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

data class LetterData(
    val id: String,
    val character: String,
    val sound: String
)

data class LettersJson(
    val french_letters: List<LetterData>,
    val arabic_letters: List<LetterData>
)

class ProgressManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("kids_learning_progress", Context.MODE_PRIVATE)
    private val gson = Gson()
    
    init {
        // Nettoyer les anciennes données corrompues au démarrage
        cleanCorruptedData()
    }
    
    private fun cleanCorruptedData() {
        val editor = prefs.edit()
        val allEntries = prefs.all
        
        for ((key, value) in allEntries) {
            // Si la valeur n'est pas une String valide pour LetterStatus, la supprimer
            if (value !is String) {
                editor.remove(key)
            } else {
                try {
                    LetterStatus.valueOf(value)
                } catch (e: Exception) {
                    editor.remove(key)
                }
            }
        }
        editor.apply()
    }

    fun saveProgress(letterId: String, status: LetterStatus) {
        prefs.edit().putString(letterId, status.name).apply()
    }

    fun getProgress(letterId: String): LetterStatus {
        return try {
            // Essayer d'abord de récupérer comme String
            val statusName = prefs.getString(letterId, null)
            if (statusName != null) {
                LetterStatus.valueOf(statusName)
            } else {
                // Si pas de String, vérifier si c'est un Boolean (ancien format)
                val booleanValue = prefs.getBoolean(letterId, false)
                if (booleanValue) LetterStatus.COMPLETED else LetterStatus.LOCKED
            }
        } catch (e: Exception) {
            // En cas d'erreur, nettoyer la valeur corrompue et retourner LOCKED
            prefs.edit().remove(letterId).apply()
            LetterStatus.LOCKED
        }
    }

    fun loadLetters(): List<Letter> {
        val frenchLetters = listOf(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        ).mapIndexed { index, letter ->
            val letterId = "fr_$letter"
            Letter(
                id = letterId,
                character = letter.uppercase(),
                language = "french",
                soundFileName = "fr_$letter",
                imageResource = letter,
                status = if (index == 0) LetterStatus.FAILED else getProgress(letterId)
            )
        }
        
        val arabicLetters = listOf(
            "ا", "ب", "ت", "ث", "ج", "ح", "خ", "د", "ذ", "ر", "ز", "س", "ش",
            "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "م", "ن", "ه", "و", "ي"
        ).mapIndexed { index, arabicChar ->
            val letterNames = listOf(
                "alif", "baa", "taa", "thaa", "jimo", "haa", "khaa", "da", "dado", "ra", "zalo", "si", "shi",
                "sado", "dao", "tao", "za", "aao", "ghao", "fao", "kafo", "kaf", "lamo", "mimo", "nono", "hao", "waw", "ya"
            )
            val letterId = "ar_$index"
            Letter(
                id = letterId,
                character = arabicChar,
                language = "arabic",
                soundFileName = "ar_${letterNames[index]}.mp3",
                imageResource = letterNames[index],
                status = getProgress(letterId)
            )
        }
        
        return frenchLetters + arabicLetters
    }
}