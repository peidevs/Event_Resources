
package org.peidevs.system

import com.xlson.groovycsv.CsvParser

class Parser {
    void parse(String text, LineListener lineListener) {
        def data = CsvParser.parseCsv(text)

        data.each { def line ->
            lineListener.notify(line)
        }
    }
}

