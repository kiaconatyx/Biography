package org.wit.biography.activities

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.wit.biography.databinding.ActivityBiographyBinding
import org.wit.biography.helpers.showImagePicker
import org.wit.biography.main.MainApp
import org.wit.biography.models.Location
import org.wit.biography.models.BiographyModel
import timber.log.Timber

class BiographyPresenter(private val view: BiographyView) {

    var biography = BiographyModel()
    var app: MainApp = view.application as MainApp
    var binding: ActivityBiographyBinding = ActivityBiographyBinding.inflate(view.layoutInflater)
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    var edit = false;

    init {
        if (view.intent.hasExtra("biography_edit")) {
            edit = true
            biography = view.intent.extras?.getParcelable("biography_edit")!!
            view.showBiography(biography)
        }        registerImagePickerCallback()
        registerMapCallback()
    }
    fun doAddOrSave(title: String, description: String) {
        biography.title = title
        biography.description = description
        if (edit) {
            app.biographys.update(biography)
        } else {
            app.biographys.create(biography)
        }        view.setResult(RESULT_OK)
        view.finish()
    }
    fun doCancel() {
        view.finish()
    }
    fun doDelete() {
        view.setResult(99)
        app.biographys.delete(biography)
        view.finish()
    }
    fun doSelectImage() {
        showImagePicker(imageIntentLauncher,view)
    }
    fun doSetLocation() {
        val location = Location(52.245696, -7.139102, 15f)
        if (biography.zoom != 0f) {
            location.lat =  biography.lat
            location.lng = biography.lng
            location.zoom = biography.zoom
        }
        val launcherIntent = Intent(view,EditLocationView::class.java)
            .putExtra("location", location)
        mapIntentLauncher.launch(launcherIntent)
    }
    fun cacheBiography (title: String, description: String) {
        biography.title = title;
        biography.description = description
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Result ${result.data!!.data}")
                            biography.image = result.data!!.data!!
                            view.contentResolver.takePersistableUriPermission(biography.image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            view.updateImage(biography.image)
                        } // end of if  
                    }
                    AppCompatActivity.RESULT_CANCELED -> { } else -> { }
                }            }    }
    private fun registerMapCallback() {
        mapIntentLauncher =
            view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            Timber.i("Location == $location")
                            biography.lat = location.lat
                            biography.lng = location.lng
                            biography.zoom = location.zoom
                        } // end of if  
                    }
                    AppCompatActivity.RESULT_CANCELED -> { } else -> { }
                }            }    }}
