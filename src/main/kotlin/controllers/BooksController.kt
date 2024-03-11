package org.example.controllers

import org.example.database.DataSource
import org.example.model.Book
import org.example.repository.BooksRepository

class BooksController {
    private val booksRepository = BooksRepository()
    private val bookList = mutableListOf<Book>()

    init {
        loadBooksFromDB()
    }

    private fun loadBooksFromDB() {
        bookList.addAll(booksRepository.getBooksFromDB())
    }

    fun registerBook(
        bookTitle: String,
        bookAuthor: String,
        bookAvailableCopies: Int
    ) {

        val addedBook = Book(title = bookTitle, author = bookAuthor, availableCopies = bookAvailableCopies)

        val result = booksRepository.saveBook(addedBook)

        if(result>0){
            bookList.removeAll(bookList)
            loadBooksFromDB()
            println("Libro registrado exitosamente.")
        }else{
            println("No se pudo registrar el libro, intente de nuevo.")
        }
    }
}