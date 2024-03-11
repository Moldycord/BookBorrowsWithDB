package org.example.model

data class Book(
    val bookId: Int = 0,
    val title: String,
    val author: String,
    val availableCopies: Int
)