package lab1_createStudents

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class StudentManager {
    private val students = mutableListOf<Student>()

    // Метод для записи данных студентов в файл
    @Throws(IOException::class)
    fun write_to_txt(filePath: String, students: List<Student>) {
        val file = File(filePath)
        file.bufferedWriter().use { writer ->
            students.forEach { student ->
                writer.write(
                    "${student.id};${student.surname};${student.name};${student.patronymic ?: ""};" +
                            "${student.phone ?: ""};${student.telegram ?: ""};${student.email ?: ""};${student.git ?: ""}"
                )
                writer.newLine()
            }
        }
    }

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
                val student = Student(line)
                studentList.add(student)
            } catch (e: IllegalArgumentException) {
                println("Ошибка в строке: \"$line\". Пропускаем её.")
            }
        }

        if (studentList.isEmpty()) {
            throw IllegalArgumentException("В файле нет корректных данных для студентов.")
        }

        return studentList
    }

    // Добавление студента в список
    fun createStudent(student: Student) {
        students.add(student)
    }

    // Чтение студента по ID
    fun readStudent(id: Int): Student? {
        return students.find { it.id == id }
    }

    // Получение списка всех студентов
    fun getAllStudents(): List<Student> {
        return students.toList()
    }

    // Вывод всех студентов в кратком формате
    fun displayAllStudents() {
        if (students.isEmpty()) {
            println("Нет студентов.")
        } else {
            students.forEach { println(it.getInfo()) }
        }
    }

    // Метод для тестирования записи и чтения студентов
    fun testWriteAndRead(filePath: String) {
        try {
            // Записываем студентов в файл
            write_to_txt(filePath, students)
            println("Студенты успешно записаны в файл.")

            // Очищаем текущий список студентов и читаем их из файла
            students.clear()
            val loadedStudents = read_from_txt(filePath)
            students.addAll(loadedStudents)
            println("Студенты успешно загружены из файла.")
        } catch (e: Exception) {
            println("Ошибка при работе с файлом: ${e.message}")
        }
    }
}
