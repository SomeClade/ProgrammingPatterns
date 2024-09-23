class Data_table<T>(private val data: Array<Array<T>>) {

    // Метод для получения значения элемента по индексу строки и столбца
    fun getValue(row: Int, column: Int): T {
        if (row in data.indices && column in data[row].indices) {
            return data[row][column]
        } else {
            throw IndexOutOfBoundsException("Недопустимые индексы: строка $row, столбец $column")
        }
    }

    // Метод для получения количества строк в таблице
    fun getRowCount(): Int {
        return data.size
    }

    // Метод для получения количества столбцов в таблице
    fun getColumnCount(): Int {
        return if (data.isNotEmpty()) data[0].size else 0
    }

    // Метод для отображения таблицы (опционально)
    fun displayTable() {
        for (row in data) {
            println(row.joinToString(" "))
        }
    }
}
