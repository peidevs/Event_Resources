package net.codetojoy.meetups_v1

class InfoMapper {

    def mapLine(def line) {
        def info = null 

        try {
            // TODO: remove hard-coded numbers
            def fields = [:] 

            fields[Info.DATE]         = line.getAt 0 
            fields[Info.NUM_ATTENDED] = line.getAt 1
            fields[Info.VENUE]        = line.getAt 2
            fields[Info.CAT_HERDER]   = line.getAt 3
            fields[Info.SPEAKER1]     = line.getAt 4
            fields[Info.TOPIC1]       = line.getAt 5
            fields[Info.SPEAKER2]     = line.getAt 6
            fields[Info.TOPIC2]       = line.getAt 7
            fields[Info.SPONSORS]     = line.getAt 8
            fields[Info.AV_SUPPORT]   = line.getAt 9

            info = new Info(fields: fields)
        } catch(Exception ex) {
            System.err.println("TRACER caught ex : ${ex.message}")
        }

        return info
    }
}
