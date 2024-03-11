package org.example.repository

import org.example.database.DataSource
import org.example.model.Student

class StudentsRepository {

    fun saveStudent(student: Student): Int {
        val query =
            "INSERT INTO students(student_name,student_first_last_name,student_second_last_name,student_enrollment_id)VALUES" +
                    " ('${student.names}','${student.firstLastName}','${student.secondLastName}',${student.enrollmentId})"

        val affectedRows = DataSource.executeSqlQueryNoResult(query)
        return affectedRows ?: 0
    }

    fun getStudentsFromDataSource(): List<Student> {
        val result = DataSource.executeSqlQueryForResult("SELECT * FROM students")
        val studentList = mutableListOf<Student>()
        result?.let {
            while (it.next()) {
                val id = it.getInt("student_id")
                val studentName = it.getString("student_name")
                val studentFirstLastName = it.getString("student_first_last_name")
                val studentSecondLastName = it.getString("student_second_last_name")
                val studentEnrollmentId = it.getLong("student_enrollment_id")

                studentList.add(
                    Student(
                        id,
                        studentName,
                        studentFirstLastName,
                        studentSecondLastName,
                        studentEnrollmentId
                    )
                )

            }
        }
        return studentList
    }
}