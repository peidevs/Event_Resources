
package org.peidevs.system

import org.peidevs.custom.*

class Runner {

    def buildInfos(def infile) {
        def text = new File(infile).getText()
        def accumulator = new Accumulator()
        Config.parser.parse(text, accumulator)
        accumulator.infos
    }

    def generateOutput(def countMap) {
        countMap.each { k, v ->
            println "${k},${v}"
        }
    }

    def run(def infile) {
        def infos = buildInfos(infile)
        def countMap = new Counter().countEntries(infos)
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