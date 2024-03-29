
package org.peidevs.utils

class Utils {
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
