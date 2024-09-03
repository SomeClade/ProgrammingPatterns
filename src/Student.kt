data class Student(
    val id: Int,
    var surname: String,
    var name: String,
    var patronymic: String,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null,
    var git: String? = null
) {
    // data class сделает всё сам
}

fun main() {
    val student = Student(
        id = 1,
        surname = "Иванов",
        name = "Иван",
        patronymic = "Иванович",
        phone = "+71234567890",
        telegram = "@ivanov",
        email = "ivanov@example.com",
        git = "https://github.com/ivanov"
    )


    println("ФИО: ${student.surname} ${student.name} ${student.patronymic}")
    println("Телефон: ${student.phone}")
    println("Телеграм: ${student.telegram}")
    println("Почта: ${student.email}")
    println("Гит: ${student.git}")


    student.phone = "+79876543210"
    println("Обновленный телефон: ${student.phone}")
}
