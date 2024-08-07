package com.ssk.run.presentation.runoverviewscreen.mapper

import com.ssk.core.domain.run.Run
import com.ssk.core.presentation.ui.formatted
import com.ssk.core.presentation.ui.toFormattedKm
import com.ssk.core.presentation.ui.toFormattedKmh
import com.ssk.core.presentation.ui.toFormattedMeters
import com.ssk.core.presentation.ui.toFormattedPace
import com.ssk.run.presentation.runoverviewscreen.model.RunUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Run.toRunUi(): RunUi {
    val dateTimeInLocalTime = dateTimeUtc
        .withZoneSameInstant(ZoneId.systemDefault())
    val formattedDateTime = DateTimeFormatter
        .ofPattern("mmm dd, yyyy - hh:mma")
        .format(dateTimeInLocalTime)

    val distanceInKm = distanceInMeters / 1000.0

    return RunUi(
        id = id!!,
        duration = duration.formatted(),
        dateTime = formattedDateTime,
        distance = distanceInKm.toFormattedKm(),
        avgSpeed = avgSpeedInKmh.toFormattedKmh(),
        maxSpeed = maxSpeedKmh.toFormattedKmh(),
        pace = duration.toFormattedPace(distanceInKm),
        totalElevation = totalElevationMeters.toFormattedMeters(),
        mapPictureUrl = mapPictureUrl
    )
}