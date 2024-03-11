package org.example.controllers

import org.example.database.DataSource
import org.example.model.Student
import org.example.repository.StudentsRepository

class StudentsController {
    private val studentsRepository = StudentsRepository()
    private val studentsList = mutableListOf<Student>()

    init {
        loadStudentsFromDb()
    }

    private fun loadStudentsFromDb() {
        studentsList.addAll(studentsRepository.getStudentsFromDataSource())
    }

    fun registerStudent(
        name: String, firstLastName: String, secondLastName: String, enrollmentId: Long
    ) {

        val addedStudent = Student(
            names = name, firstLastName = firstLastName, secondLastName = secondLastName, enrollmentId = enrollmentId
        )

        val result = studentsRepository.saveStudent(addedStudent)
        if (result > 0) {
            studentsList.removeAll(studentsList)
            loadStudentsFromDb()
            println("Se registró el estudiante correctamente")
        } else {
            println("Hubo un error al registrar al estudiante, intente de nuevo")
        }
    }
}