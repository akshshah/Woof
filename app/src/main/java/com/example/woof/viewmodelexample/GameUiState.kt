package com.example.woof.viewmodelexample

data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false,
    val currentWordCount: Int = 1,
    val score: Int = 0
)
