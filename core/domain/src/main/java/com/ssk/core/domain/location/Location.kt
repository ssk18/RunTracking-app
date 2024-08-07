package com.ssk.core.domain.location

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Location(
    val lat: Double,
    val long: Double
) {
    fun distanceTo(other: Location): Float {
        val latDistance = Math.toRadians(other.lat - lat)
        val lonDistance = Math.toRadians(other.long - long)

        val a = sin(latDistance / 2) * sin(latDistance / 2) +
                cos(Math.toRadians(lat)) * cos(Math.toRadians(other.lat)) *
                sin(lonDistance / 2) * sin(lonDistance / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return EARTH_RADIUS * c.toFloat()
    }

    companion object {
        const val EARTH_RADIUS = 6_371_000
    }
}
