
package net.codetojoy.meetups_v1

import net.codetojoy.system.Parser

class Config {
    def parser
    def outputHeader = Info.getHeader()

    def Config() {
        def infoMapper = new InfoMapper()
        parser = new Parser(infoMapper: infoMapper)
    }
}

