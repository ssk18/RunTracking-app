package com.ssk.run.presentation.runoverviewscreen

import com.ssk.run.presentation.runoverviewscreen.model.RunUi

data class RunOverviewState(
    val runs: List<RunUi> = emptyList()
)