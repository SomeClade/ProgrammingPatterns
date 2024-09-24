package lab1_createStudents

import Data_list
import Data_table

class StudentDataList(students: List<Student>) : Data_list<Student>(students) {

    // Переопределяем метод для получения наименований атрибутов
    override fun get_names(): List<String> {
        return listOf("Фамилия", "Имя", "Отчество", "Телефон", "Telegram", "Email", "Git")
    }

    // Переопределяем метод для создания объекта Data_table
    override fun get_data(): Data_table<String> {
        val data = mutableListOf<Array<String>>()

        // Добавляем сгенерированные номера и атрибуты для каждого студента
        for ((index, student) in (super.get_selected().map { it to elements[it] })) {
            val row = arrayOf(
                (index + 1).toString(), // № по порядку
                student.surname,
                student.name,
                student.patronymic ?: "",
                student.phone ?: "",
                student.telegram ?: "",
                student.email ?: "",
                student.git ?: ""
            )
            data.add(row)
        }

        return Data_table(data.toTypedArray())
    }
}
