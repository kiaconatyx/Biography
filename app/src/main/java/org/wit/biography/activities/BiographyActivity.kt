package org.wit.biography.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false
        binding = ActivityBiographyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        if (intent.hasExtra("biography_edit")) {
            edit = true
            biography = intent.extras?.getParcelable("biography_edit")!!
            binding.biographyTitle.setText(biography.title)
            binding.biographyDescription.setText(biography.description)
            binding.biographyISBN.setText(biography.ISBN)
            binding.biographyAuthor.setText(biography.author)
            binding.btnAdd.setText(R.string.save_biography)
        }

        binding.btnAdd.setOnClickListener() {
            biography.title = binding.biographyTitle.text.toString()
            biography.description = binding.biographyDescription.text.toString()
            biography.author = binding.biographyAuthor.text.toString()
            biography.ISBN = binding.biographyISBN.text.toString()
            if (biography.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_biography_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.biographys.update(biography.copy())
                } else {
                    app.biographys.create(biography.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_biography, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}
