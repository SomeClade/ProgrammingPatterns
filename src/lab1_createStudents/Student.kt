package lab1_createStudents

class Student(
    val id: Int,
    val surname: String,
    val name: String,
    val patronymic: String? = null,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
) {
    var phone: String? = phone
        set(value) {
            if (value != null && !Factory.isPhoneNumberValid(value)) {
                throw IllegalArgumentException("Неверный формат телефона: $value")
            }
            field = value
        }

    var telegram: String? = telegram
        set(value) {
            if (value != null && !Factory.validateTelegram(value)) {
                throw IllegalArgumentException("Неверный формат Telegram: $value")
            }
            field = value
        }

    var email: String? = email
        set(value) {
            if (value != null && !Factory.validateEmail(value)) {
                throw IllegalArgumentException("Неверный формат email: $value")
            }
            field = value
        }

    var git: String? = git
        set(value) {
            if (value != null && !Factory.validateGit(value)) {
                throw IllegalArgumentException("Неверный формат Git: $value")
            }
            field = value
        }

    init {
        if (!validate()) {
            throw IllegalArgumentException("Студент должен иметь хотя бы один контакт или Git.")
        }
    }

    // Конструктор для парсинга строки
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

    // Отображение информации о студенте
    fun displayInfo() {
        println("""
            ID: $id
            Фамилия: $surname
            Имя: $name
            Отчество: ${patronymic ?: "не указано"}
            Телефон: ${phone ?: "не указан"}
            Телеграм: ${telegram ?: "не указан"}
            Почта: ${email ?: "не указана"}
            Гит: ${git ?: "не указан"}
        """.trimIndent())
    }

    // Краткая информация (фамилия, инициалы, Git, контакты)
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

    // Валидация наличия Git и хотя бы одного контакта
    fun validate(): Boolean {
        return hasGit() || hasContact()
    }

    fun hasGit(): Boolean {
        return git != null && git!!.isNotBlank()
    }

    fun hasContact(): Boolean {
        return !phone.isNullOrBlank() || !telegram.isNullOrBlank() || !email.isNullOrBlank()
    }

    // Установка контактов
    fun setContacts(phone: String? = null, telegram: String? = null, email: String? = null) {
        this.phone = phone
        this.telegram = telegram
        this.email = email
    }

    companion object Factory {
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val gitRegex = Regex("^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?\$")

        fun isPhoneNumberValid(phone: String): Boolean {
            return phoneRegex.matches(phone)
        }

        fun validateTelegram(telegram: String): Boolean {
            return telegramRegex.matches(telegram)
        }

        fun validateEmail(email: String): Boolean {
            return emailRegex.matches(email)
        }

        fun validateGit(git: String): Boolean {
            return gitRegex.matches(git)
        }
    }
}
