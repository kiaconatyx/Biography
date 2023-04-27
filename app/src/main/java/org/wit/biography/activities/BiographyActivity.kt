package org.wit.biography.activities

import android.content.Intent
import android.net.Uri
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
import com.squareup.picasso.Picasso
import org.wit.biography.models.Location

class BiographyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBiographyBinding
    var biography = BiographyModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val IMAGE_REQUEST = 1
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    //var location = Location(52.245696, -7.139102, 15f)


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
            if (biography.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_biography_image)
                binding.biographyLocation.setOnClickListener {
                    val location = Location(52.245696, -7.139102, 15f)
                    if (biography.zoom != 0f) {
                        location.lat =  biography.lat
                        location.lng = biography.lng
                        location.zoom = biography.zoom
                    }
                    val launcherIntent = Intent(this, MapActivity::class.java)
                        .putExtra("location", location)
                    mapIntentLauncher.launch(launcherIntent)
                }
            }
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
                            biography.image = result.data!!.data!!
                            Picasso.get()
                                .load(biography.image)
                                .into(binding.biographyImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            biography.lat = location.lat
                            biography.lng = location.lng
                            biography.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
    }






}
