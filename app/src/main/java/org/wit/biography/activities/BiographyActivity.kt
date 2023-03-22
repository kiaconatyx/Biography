package org.wit.biography.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.wit.biography.R
import org.wit.biography.databinding.ActivityBiographyBinding
import org.wit.biography.helpers.showImagePicker
import org.wit.biography.main.MainApp
import org.wit.biography.models.BiographyModel
import timber.log.Timber
import timber.log.Timber.i

class BiographyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBiographyBinding
    var biography = BiographyModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val IMAGE_REQUEST = 1

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
            binding.biographybookcount.setText(biography.bookcount)
            binding.btnAdd.setText(R.string.save_biography)
            Picasso.get()
                .load(biography.image)
                .into(binding.biographyImage)
        }

        binding.btnAdd.setOnClickListener() {
            biography.title = binding.biographyTitle.text.toString()
            biography.description = binding.biographyDescription.text.toString()
            biography.author = binding.biographyAuthor.text.toString()
            biography.ISBN = binding.biographyISBN.text.toString()
            biography.bookcount = binding.biographybookcount.text.toString().toInt()
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

        binding.chooseImage.setOnClickListener {
            i("Select image")
        }
        registerImagePickerCallback()
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
    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }





}
