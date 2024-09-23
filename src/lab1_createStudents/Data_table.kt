class Data_table<T>(private val data: Array<Array<T>>) {

    // Конструктор принимает двумерный массив и сохраняет его в поле
    init {

    }

    // Метод для получения значения элемента по индексу
    fun getValue(row: Int, column: Int): T {
        return data[row][column]
    }

    // Метод для получения количества строк
    fun getRowCount(): Int {
        return data.size
    }

    // Метод для получения количества колонок в конкретной строке
    fun getColumnCount(row: Int): Int {
        return if (row < data.size) data[row].size else 0
    }

    // Метод для отображения таблицы (опционально)
    fun displayTable() {
        for (row in data) {
            println(row.joinToString(" "))
        }
    }
}
