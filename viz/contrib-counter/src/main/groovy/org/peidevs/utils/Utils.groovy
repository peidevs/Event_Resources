
package org.peidevs.utils

import java.text.NumberFormat

class Utils {
    def quote(def s) {
        def dq = '"'
        return "${dq}${s}${dq}"
    }

    def buildList(def list) {
        def buffer = new StringBuilder()
        def lastIndex = list.size() - 1

        list.eachWithIndex { item, index ->
            buffer.append(quote(item))

            if (index != lastIndex) {
                buffer.append(",")
            }
        }

        return buffer.toString()
    }

    def getValuesFromField(def field) {
        def results = []
        if (field.contains("/")) {
            def tokens = field.split("/")
            assert 2 == tokens.length
            results << tokens[0].trim()
            results << tokens[1].trim()
        } else {
            def trimField = field.trim()
            if (trimField) {
                results << trimField
            }
        }
        results
    }

    def getValuesFromList(def listString) {
        def tokens = listString.split(",")
        tokens.collect{ it.trim() }.findAll { ! it.isEmpty() }
    }
}
