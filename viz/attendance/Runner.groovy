
import groovy.transform.ToString
import java.time.Month 

/*
input:
Date,#,Venue,Cat Herder,Type,Speaker 1, Topic 1,Speaker 2, Topic 2,Sponsors,A/V Support,Type Context
FEB 2022,15,SZ,Ron Myers,,James O'Halloran,TinaCMS,,,"Forestry,silverorange,Binary Star,Torq IT",,
JAN 2022,31,SZ,Nolan Phillips,,Steven Baker,xUnit Pattern,,,"Forestry,silverorange,Binary Star",,

output:
[new Date(2022,0,1),217,$avg],
[new Date(2022,1,1),250,$avg],
[new Date(2022,2,1),375,$avg],
*/

@ToString
class RefInfo {
    def month
    def ordinal
}

final def refInfos = Month.values().collect { month ->
    // e.g. "JAN", 0
    //      ...
    //      "DEC", 11
    def shortMonth = month.toString().substring(0,3)
    def value = month.value - 1
    new RefInfo(month: shortMonth, ordinal: value)
}

@ToString
class Info {
    def month
    def year
    def numAttendance
}

def getDataFromLine = { line -> 
    def tokens = line.trim().split(",")
    def dateTokens = tokens[0].split(" ")
    def month = refInfos.find{ it.month == dateTokens[0]}.ordinal
    def year = dateTokens[1]
    def numAttendance = tokens[1]

    def result = new Info(month: month, year: year, numAttendance: numAttendance)
    return result
}

def getDataFromFile = { file -> 
    def result = []
    def isHeader = true
    file.eachLine { line -> 
        if (!isHeader) {
            result << getDataFromLine(line)
        }
        isHeader = false
    }
    return result
}

// e.g. SEP -> 8 
def getMonth = { monthStr -> 
    return refInfos.find{ it.month == monthStr }.ordinal
}

// test
refInfos.each { testInfo ->
    assert getMonth(testInfo.month) == testInfo.ordinal
}

def buildTokenFromRec = { rec, average ->
    def result = "[new Date(${rec.year},${rec.month},1),${rec.numAttendance},${average}],"
    return result
}

def testInfo = new Info(year: 2022, month: 10, numAttendance: 5150)
assert buildTokenFromRec(testInfo,123) == '[new Date(2022,10,1),5150,123],'

def NEW_LINE = "\n"

def buildToken = { infos, average ->
    return infos.collect { buildTokenFromRec(it, average) }.join(NEW_LINE)
}

final String SUBSTITUTION_TOKEN = "__PEIDEVS_DATA"

def writeFile = { outputFile, templateFile, infos ->
    outputFile.withWriter { writer ->
        templateFile.eachLine { line -> 
            def isToken = line.trim() == SUBSTITUTION_TOKEN
            if (isToken) {
                writer.write(infos)
            } else {
                writer.write(line)
            }
            writer.write("\n")
        }
    }
}

def getAverageAttendance = { infos ->
    def count = infos.size()
    def total = infos.sum { it.numAttendance as int }
    def result = total / count as int
    return result
}

// ---------- main

if (args.length < 3) {
    if (args.length >= 1 && args[0] == "test") {
        // no-op: run tests
        System.exit 0 
    } else {
        System.err.println "usage: groovy Runner.groovy csv-file template-html output-html"
        System.exit -1 
    }
}

def csvFile = new File(args[0])
def templateHtml = new File(args[1])
def outputHtml = new File(args[2]) 

assert csvFile.exists() && csvFile.isFile()
assert templateHtml.exists() && templateHtml.isFile()

def infos = getDataFromFile(csvFile)
def average = getAverageAttendance(infos)
def data = buildToken(infos, average)
writeFile(outputHtml, templateHtml, data)

println "Ready."
