
package net.codetojoy.utils

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
            buffer.append("${quote(item)}")

            if (index != lastIndex) {
                buffer.append(",")
            }
        }

        return buffer.toString()
    }

    def cleanTotal(def s) {
        def result = 0

        if (s) {
            def trimStr = s.replaceAll(/\$/, '').replaceAll('"', '').trim()
            result = NumberFormat.getInstance().parse(trimStr)
        }

        return result
    }
}
