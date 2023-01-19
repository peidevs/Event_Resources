
package org.peidevs.system

import com.xlson.groovycsv.CsvParser

class Parser {
    void parse(String text, LineListener lineListener) {
        CsvParser.parseCsv(text).each { def line ->
            lineListener.notify(line)
        }
    }
}

