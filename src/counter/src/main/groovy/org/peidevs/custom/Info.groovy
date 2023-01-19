
package org.peidevs.custom

import org.peidevs.utils.Utils

class Info {
    def values = []

    Info(def values) {
        this.values = values.collect { it.trim() }.findAll { ! it.isEmpty() }
    }

    static String getHeader() {
        "N/A" // utils.buildList(["key","value"])
    }

    String toString() {
        new Utils().buildList(values)
    }
}
