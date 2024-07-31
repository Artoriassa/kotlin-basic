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

    @Test
    fun `should return multi sequence labels from A to ZZZ`() {
        val expectation = arrayOf("Z", "AA", "AB").joinToString()
        val result = sut.numberToColumnLabel(26, 3).joinToString()
        assertEquals(expectation, result)
    }

    @Test
    fun `should throw error if starting sequence number less than 1`() {
        assertThrows(IllegalArgumentException::class.java) {
            sut.numberToColumnLabel(0, 2).joinToString()
        }
    }

    @Test
    fun `should throw error if number of results less than 1`() {
        assertThrows(IllegalArgumentException::class.java) {
            sut.numberToColumnLabel(3, 0).joinToString()
        }
    }
}