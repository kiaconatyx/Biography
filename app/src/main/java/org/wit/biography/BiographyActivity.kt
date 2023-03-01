package org.wit.biography

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class BiographyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biography)
        Timber.plant(Timber.DebugTree())
        Timber.i("Biography Activity started..")

    }
}