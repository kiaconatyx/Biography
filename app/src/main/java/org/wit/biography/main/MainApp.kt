package org.wit.biography.main


import android.app.Application
import org.wit.biography.models.BiographyMemStore
import org.wit.biography.models.BiographyModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    //val biographys = ArrayList<BiographyModel>()
    val biographys = BiographyMemStore()
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Biography started")
       // biographys.add(BiographyModel("One", "About one...", "Unique Code one", "Creator of One"))
        //biographys.add(BiographyModel("Two", "About two...", "Unique Code two", "Creator of Two"))
       // biographys.add(BiographyModel("Three", "About three...", "Unique Code three", "Creator of Three"))
    }
}
