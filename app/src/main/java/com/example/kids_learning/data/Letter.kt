package com.example.kids_learning.data

data class Letter(
    val id: String,
    val character: String,
    val language: String, // "french" or "arabic"
    val soundFileName: String,
    val imageResource: String, // nom du fichier PNG
    var status: LetterStatus = LetterStatus.LOCKED
)

enum class LetterStatus {
    LOCKED,    // Gris - pas encore accédé
    FAILED,    // Rouge - échoué
    COMPLETED  // Vert - réussi
}