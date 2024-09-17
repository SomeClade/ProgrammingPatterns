package lab1_createStudents

class Student_short(
    id: Int,
    surname: String,
    name: String,
    git: String? = null,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null
) : Person(id, surname, name, null, git, phone, telegram, email) {

    // Конструктор из объекта Student
    constructor(student: Student) : this(
        id = student.id,
        surname = student.surname,
        name = student.name,
        git = student.git,
        phone = student.phone,
        telegram = student.telegram,
        email = student.email
    )

    // Конструктор из строки (аналогичен конструктору Student)
    constructor(id: Int, data: String) : this(
        id = id,
        surname = data.split(";")[0],
        name = data.split(";")[1],
        git = data.split(";").getOrNull(2),
        phone = data.split(";").getOrNull(3),
        telegram = data.split(";").getOrNull(4),
        email = data.split(";").getOrNull(5)
    )

    // Метод для вывода краткой информации о студенте
    override fun displayInfo() {
        println("""
            ID: $id
            Фамилия и инициалы: ${getSurnameAndInitials()}
            Git: ${git ?: "не указан"}
            Контакт: ${getContactInfo()}
        """.trimIndent())
    }
}
