
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
    def month
    def ordinal
    def normalized
}
def testInfos = []
testInfos << new TestInfo(month: "JAN", ordinal: "01", normalized: "Jan")
testInfos << new TestInfo(month: "FEB", ordinal: "02", normalized: "Feb")
testInfos << new TestInfo(month: "MAR", ordinal: "03", normalized: "Mar")
testInfos << new TestInfo(month: "APR", ordinal: "04", normalized: "Apr")
testInfos << new TestInfo(month: "MAY", ordinal: "05", normalized: "May")
testInfos << new TestInfo(month: "JUN", ordinal: "06", normalized: "Jun")
testInfos << new TestInfo(month: "JUL", ordinal: "07", normalized: "Jul")
testInfos << new TestInfo(month: "AUG", ordinal: "08", normalized: "Aug")
testInfos << new TestInfo(month: "SEP", ordinal: "09", normalized: "Sep")
testInfos << new TestInfo(month: "OCT", ordinal: "10", normalized: "Oct")
testInfos << new TestInfo(month: "NOV", ordinal: "11", normalized: "Nov")
testInfos << new TestInfo(month: "DEC", ordinal: "12", normalized: "Dec")

class Info {
    def month
    def year
    def numAttendance
}

def getDataFromLine = { line -> 
    def tokens = line.trim().split(",")
    def dateTokens = tokens[0].split(" ")
    def month = dateTokens[0]
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

// e.g. SEP -> Sep 
def getNormalizedMonth = { monthStr -> 
    def lowerMonthStr = monthStr.toLowerCase()
    def result = lowerMonthStr[0].toUpperCase() + lowerMonthStr.substring(1)
    return result
}

testInfos.each { testInfo ->
    assert getNormalizedMonth(testInfo.month) == testInfo.normalized
}

// e.g. SEP -> 09
def getMonth = { monthStr -> 
    def parser = java.time.format.DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH)
    def accessor = parser.parse(getNormalizedMonth(monthStr))
    def month = accessor.get(java.time.temporal.ChronoField.MONTH_OF_YEAR)
    return String.format("%02d", month)
}

testInfos.each { testInfo ->
    assert getMonth(testInfo.month) == testInfo.ordinal
}

def buildTokenFromRec = { rec ->
    def month = getMonth(rec.month) 
    def date = "\"${rec.year}-${month}-01\""
    def result = "[new Date(${date}),${rec.numAttendance}],"
    return result
}

def testInfo = new Info(year: 2022, month: "NOV", numAttendance: 5150)
assert buildTokenFromRec(testInfo) == '[new Date("2022-11-01"),5150],'

def buildToken = { recs ->
    def result = new StringBuilder() 
    recs.each { rec ->
        result.append(buildTokenFromRec(rec))
        result.append("\n")
    }
    return result.toString()
}

final String SUBSTITUTION_TOKEN = "__PEIDEVS_DATA"

def writeFile = { outputFile, templateFile, data ->
    outputFile.withWriter { writer ->
        templateFile.eachLine { line -> 
            def isToken = line.trim() == SUBSTITUTION_TOKEN
            if (isToken) {
                writer.write(data)
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

def data = getDataFromFile(csvFile)
def substitutionData = buildToken(data)
writeFile(outputHtml, templateHtml, substitutionData)

println "Ready."
