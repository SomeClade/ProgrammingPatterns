package lab1_createStudents

open class Person(
    val id: Int,
    val surname: String,
    val name: String,
    val git: String? = null,
    val contact: String? = null
) {
    init {
        if (!validate()) {
            throw IllegalArgumentException("Персона должна иметь хотя бы один контакт или Git.")
        }
    }

    // Валидация полей
    open fun validate(): Boolean {
        return hasGit() || hasContact()
    }

    fun hasGit(): Boolean {
        return git != null && git!!.isNotBlank() && Factory.validateGit(git!!)
    }

    fun hasContact(): Boolean {
        return !contact.isNullOrBlank()
    }

    // Метод для получения фамилии и инициалов
    fun getSurnameAndInitials(): String {
        val initials = "${name.firstOrNull() ?: ""}."
        return "$surname $initials"
    }

    // Метод для получения информации о контакте
    fun getContactInfo(): String {
        return contact ?: "Контактов нет"
    }

    // Общий метод для отображения информации
    open fun displayInfo() {
        println("""
            ID: $id
            Фамилия и инициалы: ${getSurnameAndInitials()}
            Git: ${git ?: "не указан"}
            Контакт: ${getContactInfo()}
        """.trimIndent())
    }

    // Фабрика для валидации (переносим логику валидации из предыдущего кода)
    companion object Factory {
        private val gitRegex = Regex("^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?\$")

        fun validateGit(git: String): Boolean {
            return gitRegex.matches(git)
        }
    }
}
