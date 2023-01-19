
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

    @Test
    void testCountEntries_sorted() {
        def infos = []
        infos << new Info(['a','b','c','e','d','f'])
        infos << new Info(['a','b'])
        infos << new Info(['a'])

        // test
        def result = counter.countEntries(infos)

        assertEquals(6, result.keySet().size())
        def oneTimers = []
        result.each { k, v ->
            if (v == 1) {
                oneTimers << k
            }
        }
        assertEquals(4, oneTimers.size())
        assertEquals('c', oneTimers[0])
        assertEquals('d', oneTimers[1])
        assertEquals('e', oneTimers[2])
        assertEquals('f', oneTimers[3])
    }
}
