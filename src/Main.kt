fun main() {
    val student1 = Student.create(
        id = 1,
        surname = "Иванов",
        name = "Иван",
        patronymic = "Иванович",
        phone = "+71234567890",
        telegram = "@ivanov",
        email = "ivanov@example.com",
        git = "https://github.com/ivanov"
    )

    val student2 = Student.create(
        id = 2,
        surname = "Петров",
        name = "Петр",
        patronymic = "Петрович",
        phone = "+79876543210"
    )

    val student3 = Student.create(
        id = 3,
        surname = "Петров",
        name = "Петр",
        patronymic = "Петрович",
        phone = "сергей"
    )


    student1?.displayInfo()
    println()
    student2?.displayInfo()
    println()
    student3?.displayInfo()
}
