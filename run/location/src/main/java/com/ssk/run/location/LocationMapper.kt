package com.ssk.run.location

import android.location.Location
import com.ssk.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.ssk.core.domain.location.Location(
            lat = this.latitude,
            long = this.longitude
        ),
        altitude = this.altitude
    )
}