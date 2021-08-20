package com.binyou.cat

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class CatApplication : Application(){

    companion object {
        const val KEY = "8dc14425-c697-4b53-8d83-acaf6a742095"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}