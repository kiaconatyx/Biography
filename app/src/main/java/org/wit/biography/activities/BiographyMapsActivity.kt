package org.wit.biography.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.ui.AppBarConfiguration
import org.wit.biography.databinding.ActivityBiographyMapsBinding
class BiographyMapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiographyMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBiographyMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


    }

}