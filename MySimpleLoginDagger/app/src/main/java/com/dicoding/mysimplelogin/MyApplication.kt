package com.dicoding.mysimplelogin

import android.app.Application
import com.dicoding.mysimplelogin.di.AppComponent
import com.dicoding.mysimplelogin.di.DaggerAppComponent

class MyApplication: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}