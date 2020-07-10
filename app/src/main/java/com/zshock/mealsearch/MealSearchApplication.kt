package com.zshock.mealsearch

import android.app.Application
import android.content.Context

class MealSearchApplication : Application() {

    companion object {
        var application: Application? = null

        fun getContext(): Context {
            return application!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}