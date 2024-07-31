import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
class NumberToColumnLabelCalculatorTest {
    private val sut = NumberToColumnLabelCalculator()
    @Test
    fun `should return single sequence label from A to Z`() {
        val expectation = arrayOf("Z").joinToString()
        val result = sut.numberToColumnLabel(26, 1).joinToString()
        assertEquals(expectation, result)
    }

    @Test
    fun `should return single sequence label from A to ZZZ`() {
        val expectation = arrayOf("AB").joinToString()
        val result = sut.numberToColumnLabel(28, 1).joinToString()
        assertEquals(expectation, result)
    }
}