class NumberToColumnLabelCalculator {
    fun numberToColumnLabel(start: Int, count: Int): Array<String> {

        val result = mutableListOf<String>()
        val sb = StringBuilder()
        var num = start
        num--
        sb.insert(0, ('A' + num.rem(26)))
        result.add(sb.toString())

        return result.toTypedArray()
    }
}