
package org.peidevs.system

class Counter {

    def countEntries = { def infos  ->
        def results = [:].withDefault { key -> 0 }

        infos.each { info ->
            info.values.each { value ->
                results[value] += 1
            }
        }

        return results.sort { a, b -> b.value <=> a.value }
    }
}

