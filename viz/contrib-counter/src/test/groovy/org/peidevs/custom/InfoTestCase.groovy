
package org.peidevs.custom

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class InfoTestCase {
    @Test
    void testConstructor_basic() {
        def values = ["a", "", " ", "b "]

        // test
        def info = new Info(values)

        assertEquals(2, info.values.size())
    }
}
