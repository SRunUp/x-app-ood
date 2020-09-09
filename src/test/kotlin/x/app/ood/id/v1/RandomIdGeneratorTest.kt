package x.app.ood.id.v1

import com.google.common.base.Strings
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.platform.commons.util.StringUtils

/**
 * @author shiyajun
 * @date 2020/9/9 4:49 下午
 */
internal class RandomIdGeneratorTest {

    @Test
    fun generate() {
        val logTraceIdGenerator = RandomIdGenerator()
        println(logTraceIdGenerator.generate())
    }

    @Test
    fun `get last substr splitted by dot`() {
        val logTraceIdGenerator = RandomIdGenerator()
        var actual = logTraceIdGenerator.getLastSubstrSplittedByDot("X.APP.OOD")
        assertEquals("OOD", actual)

        actual = logTraceIdGenerator.getLastSubstrSplittedByDot("X")
        assertEquals("X", actual)

        actual = logTraceIdGenerator.getLastSubstrSplittedByDot("X#APP#OOD")
        assertEquals("X#APP#OOD", actual)
    }

    @Test
    fun `get last substr splitted by dot empty`() {
        val logTraceIdGenerator = RandomIdGenerator()
        val actual = logTraceIdGenerator.getLastSubstrSplittedByDot("")
        assertEquals("", actual)
    }
}