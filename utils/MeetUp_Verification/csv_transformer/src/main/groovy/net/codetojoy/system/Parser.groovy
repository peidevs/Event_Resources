
package net.codetojoy.system

import static com.xlson.groovycsv.CsvParser.parseCsv

class Parser {
    def infoMapper

    def parseFile = { def text, def infos  ->
        def results = []
        results.addAll(infos)

        def data = parseCsv text

        data.each { def line ->
            def info = infoMapper.mapLine(line)
            if (info) {
                results << info
            } else {
                System.err.println "TRACER: skipping " + line
            }
        }

        return results
    }
}

