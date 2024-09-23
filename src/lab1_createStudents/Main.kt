package lab1_createStudents

import Data_table

fun main() {
    val studentManager = StudentManager()

    // ===== Ввод данных в программу из файла =====
    println("Чтение данных студентов из файла 'students_input.txt'...")
    val inputFilePath = "students_input.txt"

    try {
        // Чтение студентов из входного файла
        val studentsFromFile = Student.read_from_txt(inputFilePath)

        // Добавляем прочитанных студентов в менеджер
        studentsFromFile.forEach { studentManager.createStudent(it) }

        // Выводим информацию о студентах, считанных из файла
        println("\nСтуденты, считанные из файла:")
        studentManager.displayAllStudents()

    } catch (e: Exception) {
        println("Ошибка при чтении студентов из файла: ${e.message}")
    }

    // ===== Ввод данных вручную =====
    println("\nСоздание студентов вручную...")

    // Создание студентов вручную
    val student1 = Student(
        id = 1,
        surname = "Иванов",
        name = "Иван",
        patronymic = "Иванович",
        phone = "+79112223344",
        telegram = "@ivanov",
        email = "ivanov@gmail.com",
        git = "https://github.com/ivanov"
    )

    val student2 = Student(
        id = 2,
        surname = "Петров",
        name = "Петр",
        phone = "+79112223345",
        email = "petrov@gmail.com",
        git = "https://github.com/petrov"
    )

    // Добавляем созданных вручную студентов в менеджер
    studentManager.createStudent(student1)
    studentManager.createStudent(student2)

    // Используем метод setContacts для изменения контактов у студента 1
    student1.setContacts(
        newPhone = "+79113334455",
        newTelegram = "@newIvanovTelegram",
        newEmail = "ivanov.newmail@gmail.com"
    )

    // Вывод краткой информации о студентах
    println("\nКраткая информация о студенте 1:")
    println(student1.getInfo())

    println("Краткая информация о студенте 2:")
    println(student2.getInfo())

    // ===== Вывод данных в файл =====
    println("\nЗапись данных студентов в файл 'students_output.txt'...")
    val outputFilePath = "students_output.txt"

    try {
        // Записываем студентов в файл
        studentManager.write_to_txt(outputFilePath, studentManager.getAllStudents())
        println("Студенты успешно записаны в файл.")
    } catch (e: Exception) {
        println("Ошибка при записи студентов в файл: ${e.message}")
    }

    // ===== Проверка работы валидации и исключений =====
    println("\nТест некорректных данных:")
    try {
        val invalidStudent = Student(
            id = 3,
            surname = "Сидоров",
            name = "Сидор",
            phone = "12345" // Неверный формат телефона
        )
        println(invalidStudent.getInfo())
    } catch (e: IllegalArgumentException) {
        println("Ошибка: ${e.message}")
    }

    // ===== Проверка валидности студента =====
    println("\nВалидация студента 1:")
    if (student1.validate()) {
        println("Студент 1 валиден.")
    } else {
        println("Студент 1 не прошел валидацию.")
    }


    // Создание объекта Data_table с двумерным массивом строк
    val stringTable = Data_table(arrayOf(
        arrayOf("ID", "Имя", "Фамилия"),
        arrayOf("1", "Иван", "Иванов"),
        arrayOf("2", "Петр", "Петров")
    ))

    // Отображение значений
    println("Значение в (0,0): ${stringTable.getValue(0, 0)}")
    println("Количество строк: ${stringTable.getRowCount()}")
    println("Количество колонок в строке 1: ${stringTable.getColumnCount(1)}")

    // Вывод таблицы на экран
    stringTable.displayTable()
}
