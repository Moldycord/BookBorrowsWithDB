package org.example.model

import java.sql.Date

data class BookLoan(
    val id: Int,
    val studentId: Int,
    val startDate: Date,
    val endDate: Date,
    val isActive: Boolean
)
