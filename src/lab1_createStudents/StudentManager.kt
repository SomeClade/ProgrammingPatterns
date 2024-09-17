package lab1_createStudents

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class StudentManager {
    private val students = mutableListOf<Student>()

    // Метод для чтения данных студентов из файла
    @Throws(IOException::class, IllegalArgumentException::class)
    fun read_from_txt(filePath: String): List<Student> {
        val file = File(filePath)
        if (!file.exists()) {
            throw FileNotFoundException("Файл по адресу $filePath не найден.")
        }

        val studentList = mutableListOf<Student>()
        file.forEachLine { line ->
            try {
                // Создаем студента из строки и добавляем в список
                val student = Student(line)
                studentList.add(student)
            } catch (e: IllegalArgumentException) {
                // Если строка некорректна, выбрасываем исключение
                println("Ошибка в строке: \"$line\". Пропускаем её.")
            }
        }

        if (studentList.isEmpty()) {
            throw IllegalArgumentException("В файле нет корректных данных для студентов.")
        }

        return studentList
    }

    // Вывод всех студентов в кратком формате
    fun displayAllStudents() {
        if (students.isEmpty()) {
            println("Нет студентов.")
        } else {
            students.forEach { println(it.getInfo()) }
        }
    }

    // Тестирование метода с добавлением студентов из файла
    fun addStudentsFromFile(filePath: String) {
        try {
            val loadedStudents = read_from_txt(filePath)
            students.addAll(loadedStudents)
            println("Студенты успешно загружены из файла.")
        } catch (e: Exception) {
            println("Ошибка при загрузке студентов: ${e.message}")
        }
    }

    // Добавление студента в список
    fun createStudent(student: Student) {
        students.add(student)
    }

    // Поиск студента по ID
    fun readStudent(id: Int): Student? {
        return students.find { it.id == id }
    }
}
