
package org.peidevs.utils

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class UtilsTestCase {
    def utils = new Utils()

    @Test
    void testGetValues_basic() {
        // test
        def results = utils.getValues(' abc, def, ijk')

        assertEquals('abc', results[0])
        assertEquals('def', results[1])
        assertEquals('ijk', results[2])
    }

    @Test
    void testBuildList_basic() {
        // test
        def result = utils.buildList(['a','b','c'])

        def expected = /"a","b","c"/

        assertEquals(expected, result)
    }

    @Test
    void testCleanTotal_case1() {
        // test
        def result = utils.cleanTotal('$500.00' as String)

        assertEquals((float) 500, result, 0.0f)
    }

    @Test
    void testCleanTotal_case2() {
        // test
        def result = utils.cleanTotal('"$1500.00"' as String)

        assertEquals((float) 1500, result, 0.0f)
    }
}
