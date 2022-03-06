
/*
input:
Date,#,Venue,Cat Herder,Type,Speaker 1, Topic 1,Speaker 2, Topic 2,Sponsors,A/V Support,Type Context
FEB 2022,15,SZ,Ron Myers,,James O'Halloran,TinaCMS,,,"Forestry,silverorange,Binary Star,Torq IT",,
JAN 2022,31,SZ,Nolan Phillips,,Steven Baker,xUnit Pattern,,,"Forestry,silverorange,Binary Star",,

output:
[new Date('2022-03-01'),217],
[new Date('2022-02-01'),250],
[new Date('2022-01-01'),375],
*/

class TestInfo {
    def monthUpperCase
    def ordinal
    def monthTitleCase
}

def testInfos = [
    new TestInfo(monthUpperCase: "JAN", ordinal: "01", monthTitleCase: "Jan"),
    new TestInfo(monthUpperCase: "FEB", ordinal: "02", monthTitleCase: "Feb"),
    new TestInfo(monthUpperCase: "MAR", ordinal: "03", monthTitleCase: "Mar"),
    new TestInfo(monthUpperCase: "APR", ordinal: "04", monthTitleCase: "Apr"),
    new TestInfo(monthUpperCase: "MAY", ordinal: "05", monthTitleCase: "May"),
    new TestInfo(monthUpperCase: "JUN", ordinal: "06", monthTitleCase: "Jun"),
    new TestInfo(monthUpperCase: "JUL", ordinal: "07", monthTitleCase: "Jul"),
    new TestInfo(monthUpperCase: "AUG", ordinal: "08", monthTitleCase: "Aug"),
    new TestInfo(monthUpperCase: "SEP", ordinal: "09", monthTitleCase: "Sep"),
    new TestInfo(monthUpperCase: "OCT", ordinal: "10", monthTitleCase: "Oct"),
    new TestInfo(monthUpperCase: "NOV", ordinal: "11", monthTitleCase: "Nov"),
    new TestInfo(monthUpperCase: "DEC", ordinal: "12", monthTitleCase: "Dec")
]

class Info {
    def monthUpperCase
    def year
    def numAttendance
}

def getDataFromLine = { line -> 
    def tokens = line.trim().split(",")
    def dateTokens = tokens[0].split(" ")
    def month = dateTokens[0]
    def year = dateTokens[1]
    def numAttendance = tokens[1]

    def result = new Info(monthUpperCase: month, year: year, numAttendance: numAttendance)
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

// e.g. SEP -> Sep 
def getNormalizedMonth = { monthStr -> 
    def lowerMonthStr = monthStr.toLowerCase()
    def result = lowerMonthStr[0].toUpperCase() + lowerMonthStr.substring(1)
    return result
}

testInfos.each { testInfo ->
    assert getNormalizedMonth(testInfo.monthUpperCase) == testInfo.monthTitleCase
}

// e.g. SEP -> 09
def getMonth = { monthStr -> 
    def parser = java.time.format.DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH)
    def accessor = parser.parse(getNormalizedMonth(monthStr))
    def month = accessor.get(java.time.temporal.ChronoField.MONTH_OF_YEAR)
    return String.format("%02d", month)
}

testInfos.each { testInfo ->
    assert getMonth(testInfo.monthUpperCase) == testInfo.ordinal
}

def buildTokenFromRec = { rec ->
    def month = getMonth(rec.monthUpperCase) 
    def date = "\"${rec.year}-${month}-01\""
    def result = "[new Date(${date}),${rec.numAttendance}],"
    return result
}

def testInfo = new Info(year: 2022, monthUpperCase: "NOV", numAttendance: 5150)
assert buildTokenFromRec(testInfo) == '[new Date("2022-11-01"),5150],'

def NEW_LINE = "\n"

def buildToken = { infos ->
    return infos.collect { buildTokenFromRec(it) }.join(NEW_LINE)
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

// ---------- main

if (args.length < 3) {
    System.err.println "usage: groovy Runner.groovy csv-file template-html output-html"
}

def csvFile = new File(args[0])
def templateHtml = new File(args[1])
def outputHtml = new File(args[2]) 

assert csvFile.exists() && csvFile.isFile()
assert templateHtml.exists() && templateHtml.isFile()

def infos = getDataFromFile(csvFile)
def data = buildToken(infos)
writeFile(outputHtml, templateHtml, data)

println "Ready."
