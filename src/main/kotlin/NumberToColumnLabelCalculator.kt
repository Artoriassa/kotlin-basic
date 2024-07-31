class NumberToColumnLabelCalculator {
    fun numberToColumnLabel(start: Int, count: Int): Array<String> {

        val result = mutableListOf<String>()
        var current = start

        repeat(count) {
            val sb = StringBuilder()
            var num = current

            while (num > 0) {
                num--
                sb.insert(0, ('A' + num.rem(26)))
                num /= 26
            }
            result.add(sb.toString())
            current++
        }

        return result.toTypedArray()
    }
}