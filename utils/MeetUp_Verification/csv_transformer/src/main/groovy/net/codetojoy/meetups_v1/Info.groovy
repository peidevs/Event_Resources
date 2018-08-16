
package net.codetojoy.meetups_v1

import net.codetojoy.utils.Utils

class Info {
    def fields = [:]

    static final def DATE = "Date"
    static final def NUM_ATTENDED = "# (Est.)" 
    static final def VENUE = "Venue"
    static final def CAT_HERDER = "Cat Herder"
    static final def SPEAKER1 = "Speaker 1"
    static final def TOPIC1 = "Topic 1"
    static final def SPEAKER2 = "Speaker 2"
    static final def TOPIC2 = "Topic 2"
    static final def SPONSORS = "Sponsors"
    static final def AV_SUPPORT = "A/V Support"

    static final def orderedKeys = [DATE, NUM_ATTENDED, VENUE, CAT_HERDER, 
                       SPEAKER1, TOPIC1, SPEAKER2, TOPIC2, 
                       SPONSORS, AV_SUPPORT]

    static def utils = new Utils()

    static String getHeader() {
        utils.buildList(orderedKeys)
    }

    String toString() {
        def list = []
        orderedKeys.each { key -> list << fields[key] }
        utils.buildList(list)
    }
}
