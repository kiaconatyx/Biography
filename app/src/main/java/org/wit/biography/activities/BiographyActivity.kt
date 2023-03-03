package org.wit.biography.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import org.wit.biography.R
import org.wit.biography.databinding.ActivityBiographyBinding
import org.wit.biography.main.MainApp
import org.wit.biography.models.BiographyModel
import timber.log.Timber
import timber.log.Timber.i

class BiographyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiographyBinding
    var biography = BiographyModel()
    //val biographys = ArrayList<BiographyModel>()
lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBiographyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)


        app = application as MainApp
        i("Biography Activity started...")

        binding.btnAdd.setOnClickListener() {
            biography.title = binding.biographyTitle.text.toString()
            biography.description = binding.biographyDescription.text.toString()
            biography.ISBN = binding.biographyISBN.text.toString()
            biography.author = binding.biographyAuthor.text.toString()
            if (biography.title.isNotEmpty()) {
                app.biographys.add(biography.copy())
                i("add Button Pressed: ${biography}")
                for (i in app.biographys.indices)
                { i("Biography[$i]:${this.app.biographys[i]}") }
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_biography, menu)
        return super.onCreateOptionsMenu(menu)
    }
}