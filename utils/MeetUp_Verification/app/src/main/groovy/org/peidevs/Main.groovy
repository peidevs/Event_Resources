package org.peidevs

class Keys {
    final def V1_DATE = net.codetojoy.meetups_v1.Info.DATE
    final def V1_NUM_ATTENDED = net.codetojoy.meetups_v1.Info.NUM_ATTENDED
    final def V1_VENUE = net.codetojoy.meetups_v1.Info.VENUE
    final def V1_CAT_HERDER = net.codetojoy.meetups_v1.Info.CAT_HERDER
    final def V1_SPEAKER1 = net.codetojoy.meetups_v1.Info.SPEAKER1
    final def V1_TOPIC1 = net.codetojoy.meetups_v1.Info.TOPIC1
    final def V1_SPEAKER2 = net.codetojoy.meetups_v1.Info.SPEAKER2
    final def V1_TOPIC2 = net.codetojoy.meetups_v1.Info.TOPIC2
    final def V1_SPONSORS = net.codetojoy.meetups_v1.Info.SPONSORS
    final def V1_AV_SUPPORT = net.codetojoy.meetups_v1.Info.AV_SUPPORT

    final def V2_DATE = net.codetojoy.meetups_v2.Info.DATE
    final def V2_NUM_ATTENDED = net.codetojoy.meetups_v2.Info.NUM_ATTENDED
    final def V2_VENUE = net.codetojoy.meetups_v2.Info.VENUE
    final def V2_CAT_HERDER = net.codetojoy.meetups_v2.Info.CAT_HERDER
    final def V2_SPEAKER1 = net.codetojoy.meetups_v2.Info.SPEAKER1
    final def V2_TOPIC1 = net.codetojoy.meetups_v2.Info.TOPIC1
    final def V2_SPEAKER2 = net.codetojoy.meetups_v2.Info.SPEAKER2
    final def V2_TOPIC2 = net.codetojoy.meetups_v2.Info.TOPIC2
    final def V2_SPONSORS = net.codetojoy.meetups_v2.Info.SPONSORS
    final def V2_AV_SUPPORT = net.codetojoy.meetups_v2.Info.AV_SUPPORT

    final def V2_TYPE = net.codetojoy.meetups_v2.Info.TYPE
    final def V2_TYPE_CONTEXT = net.codetojoy.meetups_v2.Info.TYPE_CONTEXT
}

class Main {
    def compareVenue(def v1Venue, def v2Venue) {
        def map = [
            "CYC":"CYC",
            "CP":"Cineplex",
            "HC":"Holman Centre",
            "LEG":"Legion",
            "MCC":"Murphy's",
            "OH":"Open Hub",
            "QSC":"QSC",
            "SC":"Samuel's Coffee",
            "SZ":"Startup Zone"
        ]
        assert map[v2Venue] == v1Venue
    }

    def compare(def v1Infos, def v2Infos) {
        new Keys().with {
            v1Infos.each { v1Info ->
                def v1Date = v1Info.fields[V1_DATE]
                def results = v2Infos.findAll { it.fields[V2_DATE] == v1Date }
                assert 1 == results.size() 
                def v2Info = results[0]
                assert v1Info.fields[V1_NUM_ATTENDED] == v2Info.fields[V2_NUM_ATTENDED]

                compareVenue(v1Info.fields[V1_VENUE], v2Info.fields[V2_VENUE])

                assert v1Info.fields[V1_CAT_HERDER] == v2Info.fields[V2_CAT_HERDER]
                assert v1Info.fields[V1_SPONSORS] == v2Info.fields[V2_SPONSORS]
                assert v1Info.fields[V1_AV_SUPPORT] == v2Info.fields[V2_AV_SUPPORT]

                def type = v2Info.fields[V2_TYPE]

                if (type.trim().isEmpty()) {
                    assert v1Info.fields[V1_SPEAKER1] == v2Info.fields[V2_SPEAKER1]
                    assert v1Info.fields[V1_SPEAKER2] == v2Info.fields[V2_SPEAKER2]

                    def v1Topic1 = v1Info.fields[V1_TOPIC1]
                    def v2Topic1 = v2Info.fields[V2_TOPIC1]
                    def v1Topic2 = v1Info.fields[V1_TOPIC2]
                    def v2Topic2 = v2Info.fields[V2_TOPIC2]

                    if (v1Topic1 != v2Topic1) {
                        println "TRACER review ${v1Topic1} vs ${v2Topic1}"
                    }
                    if (v1Topic2 != v2Topic2) {
                        println "TRACER review ${v1Topic2} vs ${v2Topic2}"
                    }
                }
            }
        }
    }

    def go(def location) {
        def v1Filename = "${location}/Meetups.csv"
        assert new File(v1Filename).exists() 
        def v1Infos = new Meetups_v1_Wrapper().buildInfos(v1Filename)

        println "TRACER # v1 infos: " + v1Infos.size()

        def v2Filename = "${location}/Meetups_v2.csv"
        assert new File(v2Filename).exists() 
        def v2Infos = new Meetups_v2_Wrapper().buildInfos(v2Filename)

        println "TRACER # v2 infos: " + v2Infos.size()

        compare(v1Infos, v2Infos)
    }

    def static void main(String[] args) {
        if (args.size() == 0) {
            System.err.println "ERROR: check usage / args"
        } 
        def location = args[0]
        println "TRACER : " + location

        def main = new Main()
        main.go(location)

        println "TRACER Ready. " + new Date()
    }
}

