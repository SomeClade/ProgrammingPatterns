package lab1_createStudents

class Student(
    id: Int,
    surname: String,
    name: String,
    val patronymic: String? = null,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
) : Person(id, surname, name, git, getPrimaryContact(phone, telegram, email)) {

    companion object {
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")

        private fun getPrimaryContact(phone: String?, telegram: String?, email: String?): String? {
            return phone ?: telegram ?: email
        }

        // Валидация полей
        fun isPhoneNumberValid(phone: String): Boolean {
            return phoneRegex.matches(phone)
        }

        fun validateTelegram(telegram: String): Boolean {
            return telegramRegex.matches(telegram)
        }

        fun validateEmail(email: String): Boolean {
            return emailRegex.matches(email)
        }
    }

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
        return super.validate() || validateContacts()
    }

    private fun validateContacts(): Boolean {
        return (contact != null && (isPhoneNumberValid(contact!!) || validateTelegram(contact!!) || validateEmail(contact!!)))
    }
}
