package org.wit.biography.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.biography.R
import org.wit.biography.main.MainApp

class BiographyListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biography_list)
        app = application as MainApp
    }
}
