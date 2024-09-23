package lab1_createStudents

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class Student(
    id: Int,
    surname: String,
    name: String,
    patronymic: String? = null,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
) : Person(id, surname, name, patronymic, git, phone, telegram, email) {

    // Конструктор для создания студента из строки
    constructor(data: String) : this(
        id = data.split(";")[0].toIntOrNull() ?: throw IllegalArgumentException("Неверный ID"),
        surname = data.split(";")[1],
        name = data.split(";")[2],
        patronymic = data.split(";").getOrNull(3),
        phone = data.split(";").getOrNull(4),
        telegram = data.split(";").getOrNull(5),
        email = data.split(";").getOrNull(6),
        git = data.split(";").getOrNull(7)
    )

    // Дополнительная информация для студента
    override fun displayInfo() {
        super.displayInfo()
        println("Отчество: ${patronymic ?: "не указано"}")
    }

    // Переопределяем валидацию для проверки контактов
    override fun validate(): Boolean {
        return super.validate()
    }

    companion object Factory {
        // Регулярные выражения для валидации
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val gitRegex = Regex("^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?\$")

        // Проверка номера телефона
        fun isPhoneNumberValid(phone: String): Boolean {
            return phoneRegex.matches(phone)
        }

        // Проверка Telegram
        fun isTelegramValid(telegram: String): Boolean {
            return telegramRegex.matches(telegram)
        }

        // Проверка Email
        fun isEmailValid(email: String): Boolean {
            return emailRegex.matches(email)
        }

        // Проверка Git
        fun isGitValid(git: String): Boolean {
            return gitRegex.matches(git)
        }

        // Метод для чтения студентов из файла
        @Throws(IOException::class, IllegalArgumentException::class)
        fun read_from_txt(filePath: String): List<Student> {
            val file = File(filePath)
            if (!file.exists()) {
                throw FileNotFoundException("Файл по адресу $filePath не найден.")
            }

            val studentList = mutableListOf<Student>()
            file.forEachLine { line ->
                try {
                    // Создаем студента из строки и добавляем в список
                    val student = Student(line)
                    studentList.add(student)
                } catch (e: IllegalArgumentException) {
                    // Если строка некорректна, выбрасываем исключение
                    println("Ошибка в строке: \"$line\". Пропускаем её.")
                }
            }

            if (studentList.isEmpty()) {
                throw IllegalArgumentException("В файле нет корректных данных для студентов.")
            }

            return studentList
        }
    }

    // Метод для установки контактов
    fun setContacts(newPhone: String? = null, newTelegram: String? = null, newEmail: String? = null) {
        if (newPhone != null && !Factory.isPhoneNumberValid(newPhone)) {
            throw IllegalArgumentException("Неверный формат номера телефона")
        }
        if (newTelegram != null && !Factory.isTelegramValid(newTelegram)) {
            throw IllegalArgumentException("Неверный формат Telegram")
        }
        if (newEmail != null && !Factory.isEmailValid(newEmail)) {
            throw IllegalArgumentException("Неверный формат Email")
        }
        phone = newPhone
        telegram = newTelegram
        email = newEmail
    }

    // Метод для получения краткой информации
    fun getInfo(): String {
        val initials = "${name.firstOrNull() ?: ""}.${patronymic?.firstOrNull() ?: ""}."
        val contact = when {
            !phone.isNullOrBlank() -> "Телефон: $phone"
            !telegram.isNullOrBlank() -> "Телеграм: $telegram"
            !email.isNullOrBlank() -> "Почта: $email"
            else -> "Контактов нет"
        }
        return "Фамилия: $surname $initials, Git: ${git ?: "не указан"}, $contact"
    }
}
