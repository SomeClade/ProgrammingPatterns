package lab1_createStudents

fun main() {
    val studentManager = StudentManager()

    // Создание студентов
    val student1 = Student.Factory.createFromMap(
        mapOf(
            "id" to 1,
            "surname" to "Иванов",
            "name" to "Иван",
            "patronymic" to "Иванович",
            "phone" to "+79001234567",
            "telegram" to "@ivanov",
            "email" to "ivanov@example.com",
            "git" to "https://github.com/ivanov"
        )
    )

    val student2 = Student.Factory.createFromMap(
        mapOf(
            "id" to 2,
            "surname" to "Петров",
            "name" to "Петр",
            "patronymic" to "Петрович",
            "git" to "https://github.com/petrov"
        )
    )

    // Добавление студентов в систему
    studentManager.createStudent(student1!!)
    studentManager.createStudent(student2!!)

    // Чтение и вывод информации о студентах
    println("Информация о студенте 1:")
    studentManager.readStudent(1)?.displayInfo()

    // Обновление данных студента
    println("\nОбновление контактов студента 2:")
    studentManager.updateStudent(2, mapOf("phone" to "+79009876543", "email" to "petrov@newmail.com"))
    studentManager.readStudent(2)?.displayInfo()

    // Удаление студента
    println("\nУдаление студента 1:")
    studentManager.deleteStudent(1)
    studentManager.displayAllStudents()
}
