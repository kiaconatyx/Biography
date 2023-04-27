package org.wit.biography.main


import android.app.Application
import org.wit.biography.models.BiographyJSONStore
import org.wit.biography.models.BiographyMemStore
import org.wit.biography.models.BiographyModel
import org.wit.biography.models.BiographyStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var biographys: BiographyStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        biographys = BiographyJSONStore(applicationContext)
        i("Biography started")
    }
}
