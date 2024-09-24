open class Data_list<T : Comparable<T>>(private val elements: List<T>) {
    private val sortedElements: List<T> = elements.sorted()
    private val selectedElements = mutableSetOf<Int>() // Хранение индексов выбранных элементов

    // a. select(number): Выделить элемент по номеру
    fun select(number: Int) {
        if (number in sortedElements.indices) {
            selectedElements.add(number)
        } else {
            throw IndexOutOfBoundsException("Index $number out of bounds for list of size ${sortedElements.size}.")
        }
    }

    // b. get_selected: Получить массив id выделенных элементов (в данном случае индексы)
    fun get_selected(): List<Int> {
        return selectedElements.toList()
    }

    // c. get_names: Этот метод должен быть переопределен в наследниках
    open fun get_names(): List<String> {
        throw UnsupportedOperationException("Метод get_names должен быть реализован в наследниках.")
    }

    // d. get_data: Этот метод должен быть переопределен в наследниках
    open fun get_data(): Data_table<String> {
        throw UnsupportedOperationException("Метод get_data должен быть реализован в наследниках.")
    }

    // Метод для получения размера списка
    fun size(): Int {
        return sortedElements.size
    }
}
