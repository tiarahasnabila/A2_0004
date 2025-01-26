package com.example.uaspam.ui

import android.app.Application
import com.example.uaspam.di.AppContainer
import com.example.uaspam.di.BukuContainer

class BukuApplications:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container=BukuContainer()
    }
}