package com.example.kids_learning.data

data class Letter(
    val id: String,
    val character: String,
    val language: String,
    val soundFileName: String,
    val imageResource: String,
    var status: LetterStatus = LetterStatus.LOCKED
)

enum class LetterStatus {
    LOCKED,
    FAILED,
    COMPLETED
}