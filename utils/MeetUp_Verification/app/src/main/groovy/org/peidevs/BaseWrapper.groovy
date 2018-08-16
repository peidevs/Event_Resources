package org.peidevs

class BaseWrapper {
    def parser
    def outputHeader

    def buildInfos(def infile) {
        def infos = []
        def isHeader = true
        def header = ""

        new File(infile).eachLine { line ->
            if (isHeader) {
                isHeader = false
                header = line
            } else {
                def text = "${header}\n${line}\n"
                infos = parser.parseFile(text, infos)
            } 
        }
    
        return infos
    }
}
