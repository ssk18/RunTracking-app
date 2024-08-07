package com.ssk.run.presentation.active_run.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Polyline
import com.ssk.core.domain.location.LocationTimestamp

@Composable
fun RunTrackingPolyline(
    locations: List<List<LocationTimestamp>>
) {
    val polylines = remember(locations) {
        locations.map { locationsList ->
            locationsList.zipWithNext { location1, location2 ->
                PolylineUi(
                    location1 = location1.location.location,
                    location2 = location2.location.location,
                    color = PolylineCalculator.locationsToColor(
                        location2 = location2,
                        location1 = location1
                    )
                )
            }
        }
    }

    polylines.forEach { polyline ->
        polyline.forEach {
            Polyline(
                points = listOf(
                    LatLng(it.location1.lat, it.location1.long),
                    LatLng(it.location2.lat, it.location2.long),
                ),
                color = it.color,
                jointType = JointType.BEVEL
            )
        }

    }
}