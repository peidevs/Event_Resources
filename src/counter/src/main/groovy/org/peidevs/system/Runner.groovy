
package org.peidevs.system

import org.peidevs.custom.Config

class Runner {

    def parser = new Config().parser
    // def outputHeader

    /*
    def Runner() {
        def config = new Config()
        parser = config.parser
        // outputHeader = config.outputHeader
    }
    */

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

    def generateOutput(def countMap) {
        countMap.each { k, v ->
            println "${k},${v}"
        }
    }

    def buildCountMap(def infos) {
        def counter = new Counter()
        counter.countEntries(infos)
    }

    def run(def infile) {
        def infos = buildInfos(infile)
        def countMap = buildCountMap(infos)
        generateOutput(countMap)
    }

    def static void main(String[] args) {
        if (args.size() < 1) {
            println "Usage: groovy Runner.groovy infile"
            System.exit(-1)
        }

        def infile = args[0]
        assert new File(infile).exists()

        new Runner().run(infile)
    }
}


