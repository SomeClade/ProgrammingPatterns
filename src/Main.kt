fun main() {
    // Создаем объект через хэш-структуру
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
            "telegram" to "@petrov"
        )
    )

    val student3 = Student.Factory.createFromMap(
        mapOf(
            "id" to 3,
            "surname" to "Сидоров",
            "name" to "Сидор",
            "patronymic" to "Сидорович"
        )
    )

    student1?.displayInfo()
    student2?.displayInfo()
    student3?.displayInfo()

    // Тестирование проверки номера телефона
    println(Student.Factory.isPhoneNumberValid("+79001234567"))
    println(Student.Factory.isPhoneNumberValid("12345"))
}
