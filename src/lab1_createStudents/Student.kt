package lab1_createStudents

class Student(
    val id: Int,
    val surname: String,
    val name: String,
    val patronymic: String,
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

    // Отображение информации о студенте
    fun displayInfo() {
        println("""
            ID: $id
            Фамилия: $surname
            Имя: $name
            Отчество: $patronymic
            Телефон: ${phone ?: "не указан"}
            Телеграм: ${telegram ?: "не указан"}
            Почта: ${email ?: "не указана"}
            Гит: ${git ?: "не указан"}
        """.trimIndent())
    }

    // Валидация наличия Git и хотя бы одного контакта
    fun validate(): Boolean {
        return hasGit() && hasContact()
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

        // Создание студента через карту данных
        fun createFromMap(params: Map<String, Any?>): Student? {
            val id = params["id"] as? Int ?: return null
            val surname = params["surname"] as? String ?: return null
            val name = params["name"] as? String ?: return null
            val patronymic = params["patronymic"] as? String ?: return null
            val phone = params["phone"] as? String
            val telegram = params["telegram"] as? String
            val email = params["email"] as? String
            val git = params["git"] as? String

            return Student(id, surname, name, patronymic, phone, telegram, email, git)
        }
    }
}
