package com.example.kraftor

import android.app.Application
import com.example.kraftor.di.AppContainer
import com.example.kraftor.di.DefaultAppContainer

/**
 * Custom Application class to hold the app-wide dependency container
 * ensures repositories and services are singletons
 */
class KraftorApplication : Application() {
    // container will hold all dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Initialize the container when the app starts
        container = DefaultAppContainer(this)
    }
}
