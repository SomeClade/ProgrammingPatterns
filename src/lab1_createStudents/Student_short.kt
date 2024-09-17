package lab1_createStudents

class Student_short : Person {

    // Конструктор из объекта Student
    constructor(student: Student) : super(
        id = student.id,
        surname = student.surname,
        name = student.name,
        git = student.git,
        contact = student.getContactInfo()
    )

    // Конструктор из строки (по аналогии с заданием)
    constructor(id: Int, data: String) : super(
        id = id,
        surname = data.split(";")[0],
        name = data.split(";")[1],
        git = data.split(";").getOrNull(2),
        contact = data.split(";").getOrNull(3)
    )

    // Переопределяем метод для краткой информации
    override fun displayInfo() {
        println("""
            ID: $id
            Фамилия и инициалы: ${getSurnameAndInitials()}
            Git: ${git ?: "не указан"}
            Контакт: ${getContactInfo()}
        """.trimIndent())
    }
}
