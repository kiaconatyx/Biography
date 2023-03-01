package org.wit.biography.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.biography.databinding.ActivityBiographyBinding
import timber.log.Timber
import timber.log.Timber.i

class BiographyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiographyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBiographyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Biography Activity started...")

        binding.btnAdd.setOnClickListener() {
            val biographyTitle = binding.biographyTitle.text.toString()
            if (biographyTitle.isNotEmpty()) {
                i("add Button Pressed: $biographyTitle")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}