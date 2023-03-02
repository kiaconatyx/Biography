package org.wit.biography.main


import android.app.Application
import org.wit.biography.models.BiographyModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val biographys = ArrayList<BiographyModel>()
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Biography started")
    }
}
