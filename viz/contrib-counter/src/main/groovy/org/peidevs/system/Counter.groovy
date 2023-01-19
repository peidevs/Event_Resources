
package org.peidevs.system

class Counter {

    def countEntries = { def infos  ->
        def results = [:].withDefault { key -> 0 }

        infos.each { info ->
            info.values.each { value ->
                results[value] += 1
            }
        }

        // primary sort: by count, secondary sort: by name
        // e.g.
        // mozart,   10
        // chopin,    5
        // bach,      2
        // beethoven, 2
        // liszt,     2
        return results.sort { a, b ->
            b.value <=> a.value ?: a.key <=> b.key
        }
    }
}

