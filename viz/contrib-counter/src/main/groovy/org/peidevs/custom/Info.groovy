
package org.peidevs.custom

class Info {
    def values = []

    Info(def values) {
        this.values = values.collect { it.trim() }.findAll { ! it.isEmpty() }
    }
}
