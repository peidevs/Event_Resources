package org.peidevs

import net.codetojoy.meetups_v2.*

class Meetups_v2_Wrapper extends BaseWrapper {
    def Meetups_v2_Wrapper() {
        def config = new Config()
        parser = config.parser
        outputHeader = config.outputHeader
    }
}
