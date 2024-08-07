package com.ssk.buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.ssk.buildlogic.convention.ExtensionType
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *>,
    extensionType: ExtensionType
) {
    commonExtension.run {
        buildFeatures {
            buildConfig = true
        }

        val apiKey = gradleLocalProperties(rootDir).getProperty("API_KEY")
        val mapsApiKey = gradleLocalProperties(rootDir).getProperty("MAPS_API_KEY")
        when(extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apiKey, mapsApiKey)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, apiKey, mapsApiKey)
                        }
                    }
                    defaultConfig {
                        manifestPlaceholders["API_KEY"] = apiKey
                        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apiKey, mapsApiKey)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, apiKey, mapsApiKey)
                        }
                    }
                    defaultConfig {
                        manifestPlaceholders["API_KEY"] = apiKey
                        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(apiKey: String, mapsApiKey: String ) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *>,
    apiKey: String,
    mapsApiKey: String
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}