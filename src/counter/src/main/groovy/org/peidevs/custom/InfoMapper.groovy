
package org.peidevs.custom

import org.peidevs.utils.Utils

// Date,#,Venue,Cat Herder,Type,Speaker 1, Topic 1,Speaker 2, Topic 2,Sponsors,A/V Support,Type Context

class InfoMapper {
    static final def INDEX_DATE = 0
    static final def INDEX_NUM = 1
    static final def INDEX_VENUE = 2
    static final def INDEX_CAT_HERDER = 3
    static final def INDEX_TYPE = 4
    static final def INDEX_SPEAKER_1 = 5
    static final def INDEX_TOPIC_1 = 6
    static final def INDEX_SPEAKER_2 = 7
    static final def INDEX_TOPIC_2 = 8
    static final def INDEX_SPONSORS = 9
    static final def INDEX_AV = 10
    static final def INDEX_TYPE_CONTEXT = 11

    static final def TYPE_REGULAR = ''
    static final def TYPE_LIGHTNING = 'L'
    static final def TYPE_PANEL = 'P'

    def mapLine(def line) {
        def info = null

        try {
            def values = []
            // cat herder
            values << line.getAt(INDEX_CAT_HERDER)

            // sponsors
            // def sponsorValues = utils.getValues(line.getAt(INDEX_SPONSORS))
            // values.addAll(sponsorValues)

            // speakers
            def type = line.getAt(INDEX_TYPE)
            def trimType = type ? type.trim() : ""

            if (trimType == TYPE_REGULAR) {
                values << line.getAt(INDEX_SPEAKER_1)
                values << line.getAt(INDEX_SPEAKER_2)
            } else if (trimType == TYPE_LIGHTNING || trimType == TYPE_PANEL) {
                def contextValues = new Utils().getValues(line.getAt(INDEX_TYPE_CONTEXT))
                values.addAll(contextValues)
            } else {
                System.err.println "TRACER SEVERE ERROR ON type: ${type}"
            }

            info = new Info(values)
        } catch(Exception ex) {
            System.err.println("TRACER caught ex : ${ex.message}")
        }

        return info
    }
}

