package lab1_createStudents

open class Person(
    val id: Int,
    val surname: String,
    val name: String,
    val patronymic: String? = null,
    val git: String? = null,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null
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
        return git != null && git!!.isNotBlank() && Student.Factory.isPhoneNumberValid(git!!)
    }

    fun hasContact(): Boolean {
        return !phone.isNullOrBlank() || !telegram.isNullOrBlank() || !email.isNullOrBlank()
    }

    // Метод для получения фамилии и инициалов
    fun getSurnameAndInitials(): String {
        val initials = "${name.firstOrNull() ?: ""}."
        return "$surname $initials"
    }

    // Метод для получения информации о контакте
    fun getContactInfo(): String {
        return when {
            !phone.isNullOrBlank() -> "Телефон: $phone"
            !telegram.isNullOrBlank() -> "Телеграм: $telegram"
            !email.isNullOrBlank() -> "Почта: $email"
            else -> "Контактов нет"
        }
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
}
