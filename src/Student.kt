data class Student(
    val id: Int,
    val surname: String,
    val name: String,
    val patronymic: String,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null,
    var git: String? = null
) {
    init {
        // Проверяем телефон при инициализации
        if (phone != null && !Factory.isPhoneNumberValid(phone!!)) {
            throw IllegalArgumentException("Неверный формат телефона: $phone")
        }
    }

    fun displayInfo() {
        println(
            """
            ID: $id
            Фамилия: $surname
            Имя: $name
            Отчество: $patronymic
            Телефон: ${phone ?: "не указан"}
            Телеграм: ${telegram ?: "не указан"}
            Почта: ${email ?: "не указана"}
            Гит: ${git ?: "не указан"}
        """.trimIndent()
        )
    }

    // Метод для проверки наличия Git
    fun hasGit(): Boolean {
        return git != null && git!!.isNotBlank()
    }

    // Метод для проверки наличия хотя бы одного контакта
    fun hasContact(): Boolean {
        return !phone.isNullOrBlank() || !telegram.isNullOrBlank() || !email.isNullOrBlank()
    }

    // Метод для валидации наличия Git и контакта
    fun validate(): Boolean {
        return hasGit() && hasContact()
    }

    companion object Factory {
        private val nameRegex = Regex("^[А-Яа-яA-Za-z-]+$")
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val gitRegex = Regex("^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?\$")

        fun createFromMap(params: Map<String, Any?>): Student? {
            // Берём значения из хэша обязательные
            val id = params["id"] as? Int ?: return null
            val surname = params["surname"] as? String ?: return null
            val name = params["name"] as? String ?: return null
            val patronymic = params["patronymic"] as? String ?: return null
            // Не обязательные
            val phone = params["phone"] as? String
            val telegram = params["telegram"] as? String
            val email = params["email"] as? String
            val git = params["git"] as? String

            // Валидация
            return if (validateFIO(surname, name, patronymic) &&
                (phone == null || validatePhone(phone)) &&
                (telegram == null || validateTelegram(telegram)) &&
                (email == null || validateEmail(email)) &&
                (git == null || validateGit(git))
            ) {
                val student = Student(id, surname, name, patronymic, phone, telegram, email, git)
                if (!student.validate()) {
                    println("Ошибка: Студент должен иметь Git и хотя бы один контакт для связи.")
                    return null
                }
                student
            } else {
                println("Ошибка при создании студента с ID $id: Некорректные данные.")
                null
            }
        }

        fun isPhoneNumberValid(phone: String): Boolean {
            return phoneRegex.matches(phone)
        }

        private fun validateFIO(surname: String, name: String, patronymic: String): Boolean {
            return nameRegex.matches(surname) && nameRegex.matches(name) && nameRegex.matches(patronymic)
        }

        private fun validatePhone(phone: String): Boolean {
            return isPhoneNumberValid(phone)
        }

        private fun validateTelegram(telegram: String): Boolean {
            return telegramRegex.matches(telegram)
        }

        private fun validateEmail(email: String): Boolean {
            return emailRegex.matches(email)
        }

        private fun validateGit(git: String): Boolean {
            return gitRegex.matches(git)
        }
    }
}
