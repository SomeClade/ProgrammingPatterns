package lab1_createStudents

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
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")

        fun isPhoneNumberValid(phone: String): Boolean {
            return phoneRegex.matches(phone)
        }
    }

    // Метод для установки контактов
    fun setContacts(newPhone: String? = null, newTelegram: String? = null, newEmail: String? = null) {
        if (newPhone != null && !Factory.isPhoneNumberValid(newPhone)) {
            throw IllegalArgumentException("Неверный формат номера телефона")
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
