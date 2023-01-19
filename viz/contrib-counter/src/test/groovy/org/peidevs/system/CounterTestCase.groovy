
package org.peidevs.system

import static org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*

import org.peidevs.custom.Info

class CounterTestCase {
    def counter = new Counter()

    @Test
    void testCountEntries_basic() {
        def infos = []
        infos << new Info(['a','b','c'])
        infos << new Info(['a','b'])
        infos << new Info(['a'])

        // test
        def result = counter.countEntries(infos)

        assertEquals(3, result.keySet().size())
        assertEquals(3, result['a'])
        assertEquals(2, result['b'])
        assertEquals(1, result['c'])
    }
}
