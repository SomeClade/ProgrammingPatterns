package lab1_createStudents

class StudentManager {
    private val students = mutableListOf<Student>()

    // Создание нового студента
    fun createStudent(student: Student): Boolean {
        if (student.validate()) {
            students.add(student)
            return true
        }
        println("Ошибка: Студент с ID ${student.id} не валиден.")
        return false
    }

    // Чтение данных студента по ID
    fun readStudent(id: Int): Student? {
        return students.find { it.id == id }
    }

    // Удаление студента по ID
    fun deleteStudent(id: Int): Boolean {
        val student = readStudent(id) ?: run {
            println("Студента с таким id не существует")
            return false
        }
        return students.remove(student)
    }

    // Отображение всех студентов
    fun displayAllStudents() {
        if (students.isEmpty()) {
            println("Нет студентов.")
        } else {
            students.forEach { it.displayInfo() }
        }
    }
}
