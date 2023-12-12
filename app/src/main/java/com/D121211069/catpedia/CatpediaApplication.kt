package com.D121211069.catpedia

import android.app.Application
import com.D121211069.catpedia.data.AppContainer
import com.D121211069.catpedia.data.DefaultAppContainer

class CatpediaApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}