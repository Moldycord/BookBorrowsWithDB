package org.example.repository

import org.example.database.DataSource
import org.example.model.Book

class BooksRepository {

    fun getBooksFromDB(): List<Book> {
        val bookList = mutableListOf<Book>()
        val dataSetResult = DataSource.executeSqlQueryForResult("SELECT * FROM books")
        dataSetResult?.let {
            while (it.next()) {
                val bookId = it.getInt("book_id")
                val bookTitle = it.getString("book_title")
                val bookAuthor = it.getString("book_author")
                val availableCopies = it.getInt("book_available_copies")

                bookList.add(Book(bookId, bookTitle, bookAuthor, availableCopies))

                println("Hola")
            }
        }
        return bookList
    }


    fun saveBook(book: Book): Int {
        val query =
            "INSERT INTO books(book_title,book_author,book_available_copies)VALUES" +
                    "('${book.title}','${book.author}',${book.availableCopies})"

        val affectedRows = DataSource.executeSqlQueryNoResult(query)

        return affectedRows ?: 0
    }
}