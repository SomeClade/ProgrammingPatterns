package lab1_createStudents

fun main() {
    val studentManager = StudentManager()

    // Пример строки для парсинга
    val studentString = "3;Сидоров;Александр;Александрович;+79112223344;@sidorov;alex@gmail.com;https://github.com/sidorov"

    try {
        // Создание студента из строки
        val student3 = Student(studentString)
        studentManager.createStudent(student3)

        println("Информация о студенте 3:")
        studentManager.readStudent(3)?.displayInfo()

    } catch (e: IllegalArgumentException) {
        println("Ошибка при создании студента: ${e.message}")
    }

    studentManager.displayAllStudents()
}
