package org.example.model

data class Student(
    val studentId: Int = 0,
    val names: String,
    val firstLastName: String,
    val secondLastName: String,
    val enrollmentId: Long,
    val bookBorrowsList: List<BookLoan> = mutableListOf()
)