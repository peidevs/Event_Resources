
package org.peidevs.custom

import org.peidevs.utils.Utils

class Info {
    def values = []

    static def utils = new Utils()

    Info(def values) {
        values.each { value ->
            if (value.trim()) {
                this.values << value
            }
        }
    }

    static String getHeader() {
        "N/A" // utils.buildList(["key","value"])
    }

    String toString() {
        utils.buildList(values)
    }
}
