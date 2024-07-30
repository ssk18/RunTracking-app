package com.ssk.core.domain.location

import com.ssk.core.domain.location.Location

data class LocationWithAltitude(
    val location: Location,
    val altitude: Double
)
