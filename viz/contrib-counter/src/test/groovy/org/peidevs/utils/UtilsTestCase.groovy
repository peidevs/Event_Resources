
package org.peidevs.utils

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class UtilsTestCase {
    def utils = new Utils()

    @Test
    void testGetValuesFromField_basic() {
        // test
        def results = utils.getValuesFromField(' abc ')

        assertEquals(1, results.size())
        assertEquals('abc', results[0])
    }

    @Test
    void testGetValuesFromField_combo() {
        // test
        def results = utils.getValuesFromField(' abc/def ')

        assertEquals(2, results.size())
        assertEquals('abc', results[0])
        assertEquals('def', results[1])
    }

    @Test
    void testGetValuesFromList_basic() {
        // test
        def results = utils.getValuesFromList(' abc, def, ijk')

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
}
