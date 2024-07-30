package com.ssk.run.presentation.di

import com.ssk.run.presentation.active_run.ActiveRunViewModel
import com.ssk.run.presentation.runoverviewscreen.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val runOverviewModule = module {
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}