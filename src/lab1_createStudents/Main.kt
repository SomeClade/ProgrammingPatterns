package lab1_createStudents

fun main() {
    // Создаем объект через хэш-структуру
    val student1 = Student.createFromMap(
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

    val student2 = Student.createFromMap(
        mapOf(
            "id" to 2,
            "surname" to "Петров",
            "name" to "Петр",
            "patronymic" to "Петрович",
            "git" to "https://github.com/petrov"
        )
    )

    val student3 = Student.createFromMap(
        mapOf(
            "id" to 3,
            "surname" to "Сидоров",
            "name" to "Сидор",
            "patronymic" to "Сидорович"
        )
    )

    // Проверка студентов
    if (student1 != null) {
        student1.displayInfo()
        println("Валидность студента 1: ${student1.validate()}")
    }

    if (student2 != null) {
        student2.displayInfo()
        println("Валидность студента 2: ${student2.validate()}")
    }

    if (student3 != null) {
        student3.displayInfo()
        println("Валидность студента 3: ${student3.validate()}")
    }

    // использование setContacts
    println("\nОбновляем контакты студента 2:")
    student2?.setContacts(phone = "+79009876543", email = "petrov@newmail.com")
    student2?.displayInfo()
    println("Валидность студента 2 после обновления: ${student2?.validate()}")

    // Проверка невалидных контактов
    try {
        println("\nПытаемся установить неверный телефон студенту 3:")
        student3?.setContacts(phone = "12345")
    } catch (e: IllegalArgumentException) {
        println("Ошибка: ${e.message}")
    }
}
