package org.peidevs

import net.codetojoy.meetups_v1.*

class Meetups_v1_Wrapper extends BaseWrapper {
    def Meetups_v1_Wrapper() {
        def config = new Config()
        parser = config.parser
        outputHeader = config.outputHeader
    }
}
