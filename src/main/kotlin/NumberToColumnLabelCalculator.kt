class NumberToColumnLabelCalculator {
    fun numberToColumnLabel(start: Int, count: Int): Array<String> {
        require(start > 0) { "Starting sequence number must be greater than zero." }
        require(count > 0) { "Count must be greater than zero." }

        val result = mutableListOf<String>()
        var current = start

        repeat(count) {
            result.add(convertNumberToColumnLabel(current))
            current++
        }

        if (result.last().length > 3) {
            throw IllegalArgumentException("Sequence exceeds ZZZ.")
        }

        return result.toTypedArray()
    }

    private fun convertNumberToColumnLabel(number: Int): String {
        val sb = StringBuilder()
        var remainder = number

        while (remainder > 0) {
            remainder--
            sb.insert(0, ('A' + remainder % 26))
            remainder /= 26
        }

        return sb.toString()
    }
}