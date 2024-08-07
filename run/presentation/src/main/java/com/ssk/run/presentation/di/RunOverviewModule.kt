package com.ssk.run.presentation.di

import com.ssk.run.domain.RunningTracker
import com.ssk.run.presentation.active_run.ActiveRunViewModel
import com.ssk.run.presentation.runoverviewscreen.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val runOverviewModule = module {
    singleOf(::RunningTracker)
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}