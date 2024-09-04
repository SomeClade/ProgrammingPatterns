data class Student(
    val id: Int,
    val surname: String,
    val name: String,
    val patronymic: String,
    val phone: String? = null,
    val telegram: String? = null,
    val email: String? = null,
    val git: String? = null
) {
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

    companion object Factory { // классическая фабрика на проверку регулярками
        private val nameRegex = Regex("^[А-Яа-яA-Za-z-]+$")
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val gitRegex = Regex("^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?\$")

        fun create(
            id: Int,
            surname: String,
            name: String,
            patronymic: String,
            phone: String? = null,
            telegram: String? = null,
            email: String? = null,
            git: String? = null
        ): Student? {
            return if (validateFIO(surname, name, patronymic) &&
                (phone == null || validatePhone(phone)) &&
                (telegram == null || validateTelegram(telegram)) &&
                (email == null || validateEmail(email)) &&
                (git == null || validateGit(git))
            ) {
                Student(id, surname, name, patronymic, phone, telegram, email, git)
            } else {
                println("Ошибка при создании студента с ID $id: Некорректные данные.")
                null
            }
        }

        private fun validateFIO(surname: String, name: String, patronymic: String): Boolean {
            return nameRegex.matches(surname) && nameRegex.matches(name) && nameRegex.matches(patronymic)
        }

        private fun validatePhone(phone: String): Boolean {
            return phoneRegex.matches(phone)
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