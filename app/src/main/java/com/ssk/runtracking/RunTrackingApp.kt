package com.ssk.runtracking

import android.app.Application
import com.ssk.auth.data.data.di.authDataModule
import com.ssk.auth.presentation.di.authViewModel
import com.ssk.core.data.di.coreDataModule
import com.ssk.run.presentation.di.runOverviewModule
import com.ssk.runtracking.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RunTrackingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RunTrackingApp)
            modules(
                authViewModel,
                authDataModule,
                appModule,
                coreDataModule,
                runOverviewModule
            )
        }
    }

}
