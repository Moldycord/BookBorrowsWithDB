package org.example.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

object DataSource {


    private const val JDBC_URL = "jdbc:postgresql://localhost:5432/library_kotlin"

    lateinit var connection: Connection

    init {
        createConn()
    }

    private fun createConn() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, "development", "12345678")
        } catch (exception: SQLException) {
            println("Connection cannot be done")
            println(exception.message)
        }
    }

    fun executeSqlQueryForResult(query: String): ResultSet? {
        connection.autoCommit = false
        val statement = connection.prepareStatement(query)
        var result: ResultSet? = null
        try {
            result = statement.executeQuery()
            connection.commit()
        } catch (exception: SQLException) {
            connection.rollback()
            connection.close()
            println(exception.message)
        }
        return result
    }

    fun executeSqlQueryNoResult(query: String): Int? {
        val statement = connection.prepareStatement(query)
        var result: Int? =0
        try {
            result = statement.executeUpdate()
            connection.commit()
        } catch (exception: SQLException) {
            connection.rollback()
            connection.close()
            println(exception.message)
        }
        return result
    }

}