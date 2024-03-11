package org.example.repository

import org.example.database.DataSource
import org.example.model.Book
import org.example.model.BookLoan

class BookLoansRepository {

    fun getBookBorrows(): List<BookLoan> {
        val mutableList = mutableListOf<BookLoan>()
        val dataSetResult = DataSource.executeSqlQueryForResult("SELECT * FROM book_loans")
        dataSetResult?.let {
            while (it.next()) {
                val borrowId = it.getInt("book_loan_id")
                val studentID = it.getInt("book_loan_student_id")
                val startDate = it.getDate("book_loan_start_date")
                val endDate = it.getDate("book_loan_end_date")
                val loanState = it.getBoolean("book_loan_state")

                mutableList.add(BookLoan(borrowId, studentID, startDate, endDate, loanState))
            }
        }
        return mutableList
    }


    fun getBookLoansByStudentId(studentId: Int): List<Book> {


        return emptyList()
    }


}